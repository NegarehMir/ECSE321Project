# Architecture Description

## Subsystem Descriptions
Our system is broken down into the following subsystems:

- View: Location View, Library View
- Controller: Location Controller, Library Controller
- Model
- Persistence

#### View
The view subsystem generates the graphical user interface (GUI). Through the GUI, it displays program data to and accepts input from the user. Upon receipt, the view subsystem passes this data to relevant controller components. Controller components can update view components with new information.

The view subsystem is further broken down into Location View and Library View - the former deals with listing and helping manage home locations and the latter deals with listing and helping manage the music library. These two 'sub-subsystems' communicate together to facilitate streaming music to a home location.

#### Controller
The controller subsystem accepts and verifies user input given through the view subsystem. Controller components store validated user input into model components.

The controller subsystem is further broken down into Location Controller and Library Controller. The Location Controller manages home location data while the Library Controller manages music library data.

#### Model
The model subsystem facilitates program data manipulation and retrieval. Collectively, model components hold all program data the application uses at runtime. The model is *completely independent* of the view subsystem.

Controller components both manipulate data in and retrieve data from model components.

#### Persistence
The persistence subsystem interfaces with an external storage system that does not lose its data when the application closes. Controller components interact with the persistence subsystem to save and load data held by model components 

## Architectural Patterns
We chose to apply the Model-View-Controller (MVC) pattern across the entire project. 

#### Technical Reasons
The requirements and scope of this project dictate that this application is mostly about users viewing and then modifiying the data. It follows naturally that our main pattern should separate data from its view, since these are two significant and very different concerns. The MVC pattern separates these nicely.

#### Practical Reasons
Our team has the most experience working with this pattern. Since the instructors and TAs have spent the most time on this particular pattern, we feel they would be best positioned to assist us with this pattern as opposed to others.

#### Patterns across Applications
We decided to use the MVC pattern across all three applications (desktop, mobile, and web). The constructs of the Java language, Android framework, and PHP language allow for easy adherence to the pattern. There are no other foreseealbe reasons to add complexity by using a different pattern across the three applications.
