# vercoolapp-tech-challenge

## Contents
- [Task](#task)
  - [Questions](#questions)
  - [Answers](#answers)
- [Structure](#structure)

## Task
>_As a user I want to be able to view a list of ideas on VeryCool_
> 
>_https://verycoolapp.com/_
> 
>_Build a `REST` / `GraphQL` API to list the ideas_

>_As a user I want to be able to add a new Idea_
> 
>_Build a REST / GraphQL API to add a new idea_

### Questions
>1. [_What technologies would you choose?_](#what-technologies-would-you-choose)
>2. _What database(s) would you choose?_
>3. _What considerations would you make when building the API?_
>4. _How would you handle security of the API?_
>5. _Write the Slack message you would send to the frontend developer explaining how to use the API_

### Answers

#### What technologies would you choose?

**_Application Framework_**

For the **_main application framework_** we've chosen to use `Micronaut`. Similar to `SpringBoot` it offers a plethora
of libraries to handle `HTTP` and reads/writes to many well-known `SQL` and `NoSQL` data storages. Also, since we're
asked to secure the API, it also offers some middle layer to delegate the logic.

**_Http Adapter_**

For adapting the API operations to `HTTP` we used `Micronaut`'s modules regarding http.

## Structure
We tried to follow a specific project structure that stems from architectural patterns such as 
**_Ports and Adapters (aka Hexagonal architecture)_** and **_Clean Architecture_**. Along with these layering
patterns for fine-grained control, we've also used packaged per domain family which may contain multiple features --
coarse-grained segregation (_package by feature_).

For example:
```shell
.
content --> domain
│   ├── adapter
│   │   ├── http
│   │   └── in_memory (for demo purposes)
│   └── core
│       ├── domain
│       └── port
│           ├── driven
│           └── driver
├── project --> domain (todo)
└── config --> infrastructure (how the application is built)
```

