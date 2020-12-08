#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <bsd/string.h>
#include <fcntl.h>

int func1()
{	
     
     // Flawfinder: ignore //2
     char buffer[1024];
     printf("Please enter your user id :");
     if(fgets(buffer, 1024, stdin) != NULL)
     {
          if (!isalpha(buffer[0]))
          {   
               // Flawfinder: ignore //3
               char errormsg[1044]; 
               strlcpy(errormsg, buffer, 1024); //5 //snprintf(errormsg, 1024, "%s", buffer); 
               strlcat(errormsg, " is not  a valid ID", sizeof(errormsg)); //4 //snprintf(errormsg + 1023, sizeof(errormsg) - 1023, "%s", " is not  a valid ID");
          }
     }
     if(ferror(stdin) != 0)
     {
          /* Handle Error */

          return -1;
     }   
     return 0;
}


/* f2d and f3d are file descriptors obtained after opening files*/
int func2(int f2d) 
{   
     char *buf2;	
     size_t len;     
     ssize_t result = read(f2d, &len, sizeof(len));   
     if(result < 0)
     {
          /* Handle Error */
          return -1;
     }
     size_t buffer_size = len + 1; 
     if(buffer_size < len) //Handle unsigned int overflow
     {
          /* Handle Error */ 
          return -1;
     }
     buf2 = malloc(buffer_size);
     if(buf2 == NULL)
     {
          /* Handle Error */
          return -1;
     }
     result = read(f2d, buf2, len);  
     if(result < 0)
     {
          /* Handle Error */
          free(buf2);
          return -1;
     }   
     buf2[len] = '\0';
     free(buf2);
     return 0;
} 

int func3(int f3d)
{   
     char *buf3;
     size_t len;
     ssize_t result = read(f3d, &len, sizeof(len));
     if(result < 0)
     {
          /* Handle Error */
          return -1;
     }
     if (len > 8000) 
     { 
          perror("too long"); 
          return -1;
     }
     size_t buffer_size = len + 1; 
     buf3 = malloc(buffer_size);
     if(buf3 == NULL)
     {
          /* Handle Error */        
          return -1;
     }     
     result = read(f3d, buf3, len);
     if(result < 0)
     {
          /* Handle Error */
          free(buf3);
          return -1;

     }
     buf3[result] = '\0';  
     free(buf3);   
     return 0;      
}    



int main()
{
     char *boo = "boooooooooooooooooooooooooooooooooooooooooooooo";
     char *buffer = (char *)malloc(10 * sizeof(char));
     if(buffer == NULL)
     {
          /* Handle Error */
          return -1;
     }
     strlcpy(buffer, boo, sizeof(buffer)); //1 //snprintf(buffer, sizeof(buffer), "%s", boo);
     int result = func1();
     if(result < 0)
     {
          /* Handle Error */ 
          free(buffer);
          return -1;
     }
     char *file_name = "/tmp/tmpfile";
     FILE *aFile;
     result = unlink(file_name);
     if(result < 0)
     {
          /* Handle Error */ 
          free(buffer);
          return -1;
     }

     int fd;
     int file_mode = 600;
     fd = open(file_name, O_CREAT | O_EXCL | O_WRONLY, file_mode);
     if(fd == -1)
     {
          /* Handle Error */ 
          free(buffer);
          return -1;
     }
     aFile = fdopen(fd, "w");
     if(aFile == NULL)
     {
         /* Handle Error */ 
          free(buffer);
          return -1;
     }

     fprintf(aFile, "%s", "hello world");
     result = fclose(aFile);
     if(result != 0)
     {
          /* Handle Error */
          free(buffer);
          return -1;
     }
     free(buffer);
     return 0;
}
