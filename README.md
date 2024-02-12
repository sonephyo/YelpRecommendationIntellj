## Similarity Based Recommendation System

___

### Project Description

- The program stores records from any of the data sets and choice of key. For this project, we will be limiting the data store size to 10000 records (ignoring the rest). We choose the read the data in the json format
- We will establish a similarity metric that must include tf-idf or cosin vector values based on custom frequency tables, possibly weighted by or in conjunction with other attributes.
- We will create a GUI that allows a user to indicate one entity, and displays two similar ones.

### System Requirements
**IDE** - IntellJ

**Libraries** <ul><li>Gson for JSON parsing<a href= "https://search.maven.org/artifact/com.google.code.gson/gson/2.10.1/jar?eh=">(Link to download)</a><li>Swing for GUI</li></ul>

**Data**

### Setting up IDE

- Have IntellJ Installed on your device

- Clone the repo (for collaborators only) by click on Get From Version Control

  ![repo image](https://i.ibb.co/NT6RHt9/Screenshot-2024-02-12-at-2-22-29-PM.png)

- Set up by placing the following url <a href="https://github.com/sonephyo/YelpRecommendationIntellj.git">https://github.com/sonephyo/YelpRecommendationIntellj.git</a>
- Open the cloned folder
- Create a package in the src folder called **database** (Instruction - right click on the src folder &rarr; New &rarr; package )
- Add the json files, **business** and **reviews** json inside database folder you created
- rename (yelp_academic_dataset_business.json to **businesses.json**, yelp_academic_dataset_review.json to **reviews.json**)

