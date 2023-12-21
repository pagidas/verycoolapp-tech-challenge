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
>2. [_What database(s) would you choose?_](#what-databases-would-you-choose)
>3. [_What considerations would you make when building the API?_](#what-considerations-would-you-make-when-building-the-api)
>4. [_How would you handle security of the API?_](#how-would-you-handle-security-of-the-api)
>5. [_Write the Slack message you would send to the frontend developer explaining how to use the API_](#slack-message-to-describe-how-the-api-works)

### Answers

#### What technologies would you choose?

**_Application Framework_**

For the **_main application framework_** we've chosen to use `Micronaut`. Similar to `SpringBoot` it offers a plethora
of libraries to handle `HTTP` and reads/writes to many well-known `SQL` and `NoSQL` data storages. Also, since we're
asked to secure the API, it also offers some middle layer to delegate the logic.

**_Http Adapter_**

For adapting the API operations to `HTTP` we used `Micronaut`'s modules regarding http.

---

#### What database(s) would you choose?
We would start using a `NoSQL` database to be able to understand the domain model iteratively. Since it doesn't seem to
be a good option to upload files in neither `SQL` nor `NoSQL` databases, but rather somewhere else in a `bucket`, we
would focus on keeping the `url` where user's content has been uploaded, along with the relationships the data models
form.

Maybe some time after, we realize that a relational databases structure is more appropriate for the problem(s) we're
facing.

---

#### What considerations would you make when building the API?
Upon reading about `vercoolapp.com` and its purpose, we identified that the application is able
to keep multiple media in one place, organized in projects/collections. This made us think that
we ought to break down the application into two distinct sub-domains:
- `content` - responsible to create/read/update/delete user's media content.
- `project` - responsible to create/read/update/delete user's projects/collections that reference contents.

**_Types of content/media_**

There are various of medias that a user is able to "bookmark". We thought all of them as "files".
- `link` - `.html`.
- `photo` - `.jpg`, and others.
- `document` - `.pdf`, `.txt`, and others.

**_Functionalities_**

We've only dealt with the `content` side of things. But that is also limited.

**Content domain**

Though we haven't implemented the functionality to upload the file somewhere other than saving it
into the database; we built the flow that is able to `create` a user's content, storing information
about the media type, along with the record's `id` so that the front-end is able to request distinct
`content`s that users bookmarked. After implementation of uploading the content, we should also keep a
record of the url that can be found, saved into the database in the same flow. That url can be used from
the front-end to download the files, should the user changes device but wants to keep their bookmarks.

**Project domain**

There is an association between a project and contents.
1. User's contents should exist in the default project if no project has been assigned to them.
2. Users are able to move contents from one project into another.
3. Users can create/update/delete projects.

The above functionalities would also change a bit of the functionality in `content` module. For example,
if no `project` is used when creating a content (bookmark some media) then it should default to pre-existing
project.

**_Content-length limit_**

Usually, http web servers have a content-length limit and respond back with a client error, `413`.
As this can happen when users want to upload large-sized photos, there is a way to configure the server's
content length limit to allow some breathing room.

---

#### How would you handle security of the API?
Though securing API(s) is a non-trivial problem, we would at least like to apply two things:
- Use the `HTTPS` protocol to encrypt in-transit data using a certificate.
- Apply `Bearer` http authentication scheme.

For the second point, the quickest way we thought of is to use a trustworthy, third-party identity provider
to generate a `JWT` token using the `OIDC` (OpenID Connect) flow (assuming is supported by the provider).

This token should be enough to verify that the user is valid and thus "okay" to use our API(s). This is
only for authentication purposes and not for authorization (request-level).

---

#### Slack message to describe how the API works

> [!NOTE]
> Expand the sections clicking in the "►" before each one.

**_Content API_**

_The API contains on security yet..._

There two operations currently supported:
<details>
<summary>create user's content (media files like `.txt`, `.jpg`, `.pdf`)</summary>

**Request**
```
POST /content/user/{userId:UUID}
Content-Type: multipart/form-data
Content-Disposition: form-data; name="file"; filename="document.pdf"
Content-Type: application/pdf
```

To create a user's content we have to send a `POST` request with `form-data` payload 
and `Content-Type: multipart/form-data` header. The `form-data` payload requires a certain key-value
format, where the key **_must_** be called `file` and its value requires the file to "created".

Here's an example of a `curl` request. I hope it makes sense:
```shell
curl --location '<base_url>/content/user/6044802a-3638-4da9-a6f1-5e8c3970ad3e' \
--header 'Content-Type: multipart/form-data' \
--form 'file=@"</path>/document.pdf"'
```

**Response**

```
200 - OK
```

</details>

<details>
<summary>view all user's contents</summary>

**Request**
```
GET /content/user/{userId}
```

**Response**
```json
[
    {
        "id": {
            "value": "d590eafc-16e5-411d-89e5-445273ebd75a"
        },
        "title": {
            "value": "hey.json"
        },
        "type": "application/json"
    },
    {
        "id": {
            "value": "3cc1af20-800c-47ac-a5c1-918484b8ccd4"
        },
        "title": {
            "value": "document.pdf"
        },
        "type": "application/pdf"
    }
]
```

</details>

---

## Structure
We tried to follow a specific project structure that stems from architectural patterns such as 
**_Ports and Adapters (aka Hexagonal architecture)_** and **_Clean Architecture_**. Along with these layering
patterns for fine-grained control, we've also used packaged per domain family which may contain multiple features --
coarse-grained segregation (_package by feature_).

For example:
```shell
.
├── content --> domain
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
We've used this sort of project structure to improve project navigation, and separation of concerns. Though it's nowhere
near "perfect" at least it visualizes the backbone on how we should think when structuring a software project.

High-level architecture is deeply impacted by the low-level cohesion of modules and their dependencies.

To take the above structure one step further, we would encourage to modify the high-level packages into distinct `Gradle`
projects/modules to enforce a better dependency management and isolation.
