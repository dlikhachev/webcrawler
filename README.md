# Web Crawler

Command line utility to get assets from url.

## Compile application

To compile run `maven` command in the `project_directiry`:
```
mvn clean install
```
now you have `[project_directory]/target/web-crawler-1.0.jar` file.


## Running application

#### Prerequisites
- Java 8;

#### Running application
To run application execute the following command:
```
java -jar web-crawler-1.0.jar -DstartUrl=[url] -DincludeChildUrls=[true|false]
```

where: 
- `startUrl` - www url for crawling, string, required;
- `includeChildUrls` - crawl down the child links or not, boolean, optional; 

Example: 
```
java -jar web-crawler-1.0.jar -DstartUrl=www.gocarless.com -DincludeChildUrls=true

```



