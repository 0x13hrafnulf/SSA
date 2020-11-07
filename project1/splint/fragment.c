#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>

static void func1()
{	
     char buffer[1024];
     printf("Please enter your user id :");
     if(fgets(buffer, 1024, stdin)!=NULL)
     {
          if (!isalpha(buffer[0]))
          {   
               char errormsg[1044]; 
               strncpy(errormsg, buffer,1024);   
               strcat(errormsg, " is not  a valid ID");
          }
     }
     if(ferror(stdin) != 0)
     {
          /* Handle Error */
          
     }    
  
     
}


/* f2d and f3d are file descriptors obtained after opening files*/
void func2(int f2d) 
{   
     char *buf2;	
     size_t len;    
     ssize_t result = read(f2d, &len, sizeof(len));   
     if(result < 0)
     {
          /* Handle Error */
     }
     buf2 = malloc(len+1);   
     if(buf2 == NULL)
     {
          /* Handle Error */
          return;
     }
     result = read(f2d, buf2, len);  
     if(result < 0)
     {
          /* Handle Error */
          
     }
     buf2[len] = '\0';
     free(buf2);
} 

void func3(int f3d){   
     char *buf3;
     int len;
     ssize_t result = read(f3d, &len, sizeof(len));
     if(result < 0)
     {
          /* Handle Error */
     }
     if (len > 8000) { 
          error("too long"); 
          return; 
     }
          
     buf3 = malloc((size_t)len);  
     if(buf3 == NULL)
     {
          /* Handle Error */        
          return;
     }   
     result = read(f3d, buf3, (size_t)len);
     if(result < 0)
     {
          /* Handle Error */

     }

     free(buf3);         
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
     strcpy(buffer, boo);
     func1();

     FILE *aFile = fopen("/tmp/tmpfile", "w");
     if(aFile == NULL)
     {
          /* Handle Error */
          free(buffer);
          return -1;
     }
     fprintf(aFile, "%s", "hello world");


     int result = fclose(aFile);
     if(result != 0)
     {
          /* Handle Error */
          free(buffer);
          return -1;
     }
     free(buffer);
     return 0;
}
