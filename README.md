# yaml-to-xml
Convertor YAML into XML. Written in Java 10.
## Example
The service has controller handling POST request to change a YAML file:
```yaml 
---
sample: file
list:
    item 1
    item 2
items:
    - name: item1
      price: 10

    - name: item2
      price: 20
```
Result should be shown as an XML file:
```xml
<list>item 1 item 2
</list>
<sample>file</sample>
<items>
  <price>10</price>
  <name>item1</name>
</items>
<items>
  <price>20</price>
  <name>item2</name>
</items>
````
## Usage
- Building project
`$ ./gradlew build`
- Creating Docker image
`$ ./gradlew dockerCont`
- Starting service
`$ ./gradlew start`
- Sending POST request to convert yaml to xml
- - `$ docker run -i -p 8080:8080 converter-yaml-xml `
- - `$ curl -d sample.yaml http://127.0.0.1:8080/request`
- Commands should be run in different terminals


