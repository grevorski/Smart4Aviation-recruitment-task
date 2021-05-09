To build it, you will need to download and unpack the latest (or recent) version of Maven (https://maven.apache.org/download.cgi)
and put the `mvn` command on your path.
Then, you will need to install a Java 1.11 (or higher) JDK (not JRE!), and make sure you can run `java` from the command line.
Now you can run `mvn clean install` and Maven will compile your project, 
an put the results it in a jar file in the `target` directory.

Run SimpleHttpServer class and open http://localhost:8000/info in your browser

![image](https://user-images.githubusercontent.com/74430404/117585924-7a58c680-b115-11eb-920f-ef32862759f4.png)
For tests use  http://localhost:8000/flight/weight?flightNumber=6043&departureDate=2015-03-18T10:37:13-01:00
                http://localhost:8000/flight?IATA_Airport_Code=ANC&departureDate=2015-03-18T10:37:13-01:00
                
After `=` you can insert you own user input

![image](https://user-images.githubusercontent.com/74430404/117586046-108cec80-b116-11eb-94f7-bf250a885be6.png)
![image](https://user-images.githubusercontent.com/74430404/117586071-26021680-b116-11eb-80d6-53f1524ed7ce.png)
