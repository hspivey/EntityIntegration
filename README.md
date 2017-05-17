# Entity Integration Assignment
You are tasked with completing the integration of the Project entity into an existing application. The application is a management back end meant to store and retrieve information about Project Managers and their associated Projects. The Project and Project Manager entities already exist, but there is no repository, service, or controller for the Project entity. It will be your task to create these items, add additional fields to the entities, and add search features to allow for sensible retrieval of data.

### Tasks
- [ ] Create `Project` Repository
- [ ] Create `Project` Service
- [ ] Create `Project` Controller
- [ ] Add a `StartDate` field to the `Project` entity
- [ ] Add a `DueDate` field to the `Project` entity
- [ ] Add an endpoint to allow for the retrieval of all overdue projects
- [ ] Add an endpoint `/projectManager/{id}/project` that will retrieve a collection of all projects associated with that `ProjectManager`
- [ ] Add an endpoint to allow searching for all `ProjectManager` entities with overdue projects. In the results, display the number of overdue projects associated with each `ProjectManager`. Sort the results from largest to smallest number of overdue projects.