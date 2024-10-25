# Developer Guide

Welcome to the PlanPal Developer Guide! Thank you for taking an interest in the behind-the-scenes work of our product. We hope this document proves informative and useful for your work!

## Table of Contents
1. [Acknowledgements](#Acknowledgements)
2. [Setting up, getting started](#Setting-up-getting-started)
3. [Design](#Design)
   - [Architecture](#Architecture)
   - [UI component]()
   - [Logic component]()
   - [Model component]()
   - [Storage component]()
   - [Common classes]()
4. [Implementation]()
   - [Contact]()
     - [Add and Remove Command]()
     - [Edit Command]()
   - [Activities]()
   - [Budget]()
   - [Storage feature]()
5. [Documentation, logging, testing, configuration, dev-ops]()
6. [Appendix: Requirements]()
   - [Product scope]()
   - [User stories]()
   - [Use cases]()
   - [Non-Functional Requirements]()
   - [Glossary]()
7. [Appendix: Instructions for manual testing]()
   - [Launch and shutdown]()
   - [Create a contact]()
   - [Create an activity]()
   - [Create a budget]()

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Setting up, getting started
1. Java 17 is required for PlanPal to function properly, please make sure your device has Java 17 installed. If not, you may download it [here](). If you need further assistance installing Java 17, you may refer to the [Installation Guide.]()
2. Once you have ascertained that your device has Java 17 installed, you may download the `.jar file` [here]().
insert SS of release page

Click on `PlanPal.jar` to automatically download the file. Place the file in a folder your choice.



## Design

### Architecture

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
---
## Adding Contact
The `add` command allows users to add a contact with various categories

### Usage:
```
add /<category 1>: <value 1> /<category 2>: <value 2> /<category 3>: <value 3> ... 
```
### Example:
```
add /name: johnny
```
### Expected Output:
```
_________________________________________________________
Added successfully!
_________________________________________________________
```

---

