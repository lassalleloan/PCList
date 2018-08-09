# PCList :: A multi-tiered architecture JavaEE project

Authors: Jérémie Zanone & Loan Lassalle
***

## Introduction

The goal of this repo is to develop a multi-tiered application, allowing the user to manage a large “list of Things”. Things should be stored in a database. In our case things are PCs like the title of this file mention it.

**Constraints**
- Use MVC on the server side. Do not implement a single-page app with AJAX.
- Use JDBC to access the DB (no ORM).

## Quick start

Assuming that you have installed **docker** and **docker-compose** on your machine and cloned the repo, move to the `topology` directory and fire up docker-compose to start the 3 app servers:

```
cd topology
docker-compose up --build
```

The first time you do that, it will take a while since docker will fetch the source images from Docker Hub.

When the process is done, you should be able to access the app servers at the following URLs:

```
[Windows]
JBoss Wildfly -> http://192.168.99.100:9090/pclist (apps) and http://192.168.99.100:9990 (console -> admin/admin)

[Linux]
JBoss Wildfly -> http://localhost:9090/pclist (apps) and http://192.168.99.100:9990 (console -> admin/admin)
```

Or for phpmyadmin:

```
[Windows]
http://192.168.99.100:6060(-> root/adminpw)

[Linux]
http://localhost:6060 (-> root/adminpw)

```

If you want to erase the generated database you have to use this command in your terminal before to rebuild your images in order to put downs the docker containers.

```
docker-compose down
```
## Detailed information

Have a look at this [Youtube playlist](https://www.youtube.com/playlist?list=PLfKkysTy70Qa7tSlkbsvOrRc6Ug_c0nZz) for a detailed description of the setup
- Architecture MVC (Bootcamp 2.1, 2.2, 2.3, 2.4 and 2.5).
- Project (Bootcamp 3.1, 3.2, 3.3, and 3.4).
- Configuration (Bootcamp 4.1 to 4.8).

## Usage (WEB-app)
Once on the WEB-app contained in a nice web template (cf. Quick start) here is a list of what you can and can't do with our application:

- **CAN**
  - Generate a list of random PCs and of its components (1 PC list and 3 components lists).
  - Generate a large amount of PCs components.
  - Add a new PC and its components.
  - Delete a PC and its components.
  - Update a PC and its components.
  - Use the pagination controls to browse through the lists.
  - Use the pagination controls to choose the amount of things displayed on the page.
  - Sort the lists by clicking on the column headers.
  - See a red validation messages after an update or a deletion.

- **CAN'T**

  - Generate a large amount of PC because in order to proceed we need to create the same amount of CPU, RAM and GPU which take a lot of resource to JDBC. Furthermore we don't use asynchronous treatment.


## Modifying the code

If you want to look at the code of the apps and change it, you have to regenerate a new pclist.war file (`clean and build`) and to replace the actual one at this path ```images/wildfly/data/pclist.war```.

## Deploying the apps automatically

There are different ways to deploy .war files.

* Use the web console
* Copy of the .war file in a special directory monitored by the server (in our case ```images/wildfly/data/pclist.war```). Very interesting when combined with Docker (autodeploy).

Since you goal is to make it possible to start your entire project with a single command (`docker-compose up`), you will probably want to use the last one.
