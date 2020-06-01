# HackerNews API


#### **To run this project follow these steps:**

**Build the project:**
- `mvn clean install`

**Build the docker-compose:**
- `docker-compose build`

**Run the docker-compose file:**
- `docker-compose up`



#### **List Of API's Exposed :**
- API to fetch top 10 stories:
  - [http://localhost:8080/top-stories](http://localhost:8080/top-stories)
  
- API to fetch top 10 comments on given story id:
  -  Replcae '?' with ID of story you want to fetch in this url : http://localhost:8080/comments?storyId=?
  - Example request [http://localhost:8080/comments?storyId=8863](http://localhost:8080/comments?storyId=8863)
  
- API to fetch previously served top 10 stories:
  - [http://localhost:8080/past-stories](http://localhost:8080/past-stories)
