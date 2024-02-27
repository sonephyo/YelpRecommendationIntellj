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
- Yelp database (<a href="https://www.yelp.com/dataset/documentation/main">Link to download</a>) 
(Note - we will only use *yelp_academic_dataset_review.json* and *yelp_academic_dataset_business.json*
### Setting up IDE

- Have IntellJ Installed on your device

- Clone the repo (for collaborators only) by click on Get From Version Control

  ![repo image](https://i.ibb.co/NT6RHt9/Screenshot-2024-02-12-at-2-22-29-PM.png)

- Set up by placing the following url <a href="https://github.com/sonephyo/YelpRecommendationIntellj.git">https://github.com/sonephyo/YelpRecommendationIntellj.git</a>
- Open the cloned folder
- Create a package in the src folder called **database** (Instruction - right click on the src folder &rarr; New &rarr; package )
- Add the json files, *yelp_academic_dataset_business.json* and *yelp_academic_dataset_review.json* inside database folder you created
- rename yelp_academic_dataset_business.json to businesses.json
- rename yelp_academic_dataset_review.json to reviews.json

<div style="display: flex;flex-direction: row;gap: 10px;align-items: center">
  <img src="https://i.ibb.co/x8X7n3W/Screenshot-2024-02-27-at-5-58-38-PM.png" width="150" height="280">
  <p>The directory of your folder should be as shown.</p>
</div>

- Run the Main.java file
---
## Usage and Distribution Notice

Thank you for taking the time to explore our project! We hope you find it useful and informative. Before you proceed, we want to provide some important information regarding the usage and distribution of this project:

This project is provided for educational and personal use only. While you're welcome to browse the code, learn from it, and use it for your personal projects, we kindly request that you refrain from using it for commercial purposes or redistributing it without our explicit permission.

We've invested our time and effort into creating this project, and we appreciate your cooperation in respecting our work and intentions.

Thank you for your understanding and cooperation.

Best regards,

**Soney** and **Hsu**
