# Web Crawler

Command line utility to get assets from url.

## Compile application

To compile run `maven` command in the `project_directory`:
```
mvn clean package
```
now you have `[project_directory]/target/web-crawler-1.0-jar-with-dependencies.jar` file.


## Running application

#### Prerequisites
- Java 8;
- Maven > 3.x;

#### Running application
To run application execute the following command:
```
java -jar web-crawler-1.0-jar-with-dependencies.jar [parameters]
```

where: 
- `urlToCrawl` - www url for crawling, string, required;
- `excludeChildUrls` - exclude child urls from crawling, boolean, optional; 

Example: 
```
java -jar web-crawler-1.0-jar-with-dependencies.jar -urlToCrawl www.gocardless.com -excludeChildUrls

```



