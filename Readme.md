# Git Reach Chat

Talk easily with fellow developers using just their GitHub usernames. No more emails – connect, chat, and collaborate hassle-free for smoother coding discussions

## Tech stack

- spring boot - the backend framework used is Spring Boot ... it is a Java-based framework used to build endpoints
- cassandra - the database is Cassandra ... it is a NoSQL database that is highly scalable and fault tolerant
- next js - the frontend framework used is Next.js ... it is a React framework that is used to build server side rendered applications
- typescript - the language used is TypeScript ... it is a superset of JavaScript that adds static type definitions
- tailwind css - the CSS framework used is Tailwind CSS ... it is a utility-first CSS framework for rapidly building custom designs

### EndPoints

- `GET /login` - redirects to GitHub login page
- `GET /folders/save` - saves a folder to the database 
- `GET /folders` - returns all folders
- `POST /compose` - creates a new message
- `GET /messages` - returns all messages
- `GET /message/:id` - returns a message by id
