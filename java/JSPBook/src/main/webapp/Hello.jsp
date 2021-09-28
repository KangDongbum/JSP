<%!
/** Below Class Name **/
int num1 = 100;

void printMethod(){
	
}
int sum(int num1, int num2){
	return num1 + num2;
}
%>

<%=sum(100,200)%>

<%
/** _jspService() **/ 
int num2 = 200;
out.print("num2 : "+num2);
out.print("Hello World");
out.print(sum(100,200));

%>