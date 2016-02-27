# Architecture Description

## Subsystem Descriptions
Our system is broken down into the following subsystems:
- View
- Controller
- Model
- Persistence

#### View

#### Controller

#### Model
The model subsystem facilitates program data manipulation and retrieval. Collectively, model components hold all program data the application uses at runtime.

#### Persistence
The persistence subsystem interfaces with an external storage system that does not lose its data when the application closes. Controller components interact with the persistence subsystem to save and load data held by model components 

## Architectural Patterns
We chose to apply the Model-View-Controller (MVC) pattern across all three applications.

#### Technical Concerns
The requirements and scope of this project dictate that this application is mostly about users viewing and then modifiying the data. It follows naturally that our main pattern should separate data from its view, since these are two significant and very different concerns. The MVC pattern separates these nicely.

#### Practical Concerns
Our team has the most experience working with this pattern. Since the instructors and TAs have spent the most time on this particular pattern, we feel they would be best positioned to assist us with this pattern as opposed to others.

#### Patterns across Applications
