#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>

void func1()
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

}


/* f2d and f3d are file descriptors obtained after opening files*/
void func2(int f2d) 
{   
     char *buf2;	
     size_t len;    
     read(f2d, &len, sizeof(len)); 

     size_t buffer_size = len + 1; 
     if(buffer_size < len) //Handle unsigned int overflow
     {
          /* Handle Error */ 
     }
     buf = malloc(buffer_size);   
     read(f2d, buf2, len);  
     buf2[len] = '\0';
} 

void func3(int f3d)
{   
     char *buf3;
     int i, len;
     read(f3d, &len, sizeof(len));
     if (len > 8000) 
     { 
          error("too long"); 
          return; 
     }
     if(len < 0)
     {
          /* Handle Error */ 
     }
     int buffer_size = len + 1; 
     buf3 = malloc(buffer_size);     
     read(f3d, buf3,len);
     buf[len] = '\0';      
}


void main()
{
     char *boo = "boooooooooooooooooooooooooooooooooooooooooooooo";
     char *buffer = (char *)malloc(10 * sizeof(char));
     strlcpy(buffer, boo, sizeof(buffer)); //1 //snprintf(buffer, sizeof(buffer), "%s", boo);
     func1();

     
     char *file_name = "/tmp/tmpfile";
     FILE *aFile;
     unlink(file_name);

     int fd;
     int file_mode = 600;
     fd = open(file_name, O_CREAT | O_EXCL | O_WRONLY, file_mode);
     if(fd == -1)
     {
          /* Handle Error */
     }
     aFile = fdopen(fd, "w");
     if(aFile == NULL)
     {
         /* Handle Error */  
     }

     fprintf(aFile, "%s", "hello world");
     fclose(aFile);
}


    
