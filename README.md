# JPA

#EntityManager
1. EntityManager Persist & Update

    <p>Course course = new Course("New Course");
    em.persist(course); // Insert Query
    course.setName("New Course Updated"); // Update Query</p>

<ul>
    <li> First EntityManager will save new course, then it find with in the same
    transaction we are updating it so it will update it.
    <li> Above method is in a single transaction as we use @Transactional
    <li> EntityManager will keep track all the changes
</ul>

2. EntityManager Clear, there are 4 steps in this transaction (assume detach is not there)

    
    Course course1 = new Course("New Course");
    em.persist(course1); // Hibernate will get the ID for Course 1
    em.flush() // Changes till this point will sent out to db

    course1.setName("New Course Updated"); // Update Query
    em.flush() // Changes till this point will sent out to db

    Course course2 = new Course("New Course 2");
    em.persist(course2); // Hibernate will get the ID for Course 2
    em.flush() // Changes till this point will sent out to db
   
    em.detach(course2) // This will detach course2 from em
   
    course2.setName("New Course 2 Updated"); // Update Query
    em.flush() // Changes till this point will sent out to db
   


Insert course1
Update course1
Insert course2
Detach
Update won't call for course 2
    
    
    Course course1 = new Course("New Course");
    em.persist(course1); // Hibernate will get the ID for Course 1
    Course course2 = new Course("New Course 2");
    em.persist(course2); // Hibernate will get the ID for Course 2
    em.flush() // Changes till this point will sent out to db

    em.clear() // This will detach all objects

    course1.setName("New Course Updated"); // Update Query
    em.flush() // Changes till this point will sent out to db
   
    course2.setName("New Course 2 Updated"); // Update Query
    em.flush() // Changes till this point will sent out to db

Insert course1
Insert course2
clear
Update won't call for both course 1 & 2


2. EntityManager Refresh


    Course course1 = new Course("New Course");
    em.persist(course1); // Hibernate will get the ID for Course 1
    Course course2 = new Course("New Course 2");
    em.persist(course2); // Hibernate will get the ID for Course 2
    em.flush() // Changes till this point will sent out to db

    course1.setName("New Course Updated"); // Update Query
    course2.setName("New Course 2 Updated"); // Update Query

    em.refersh(course1) // Content of course 1 will be refresh/fetch from db, and persistence context will be updated with db
    em.flush() // Changes till this point will sent out to db only for course 2 as course 1 matches db 
    
On Refresh course 1 data will be fetched from db & update the object for course 1, this means last
state of course 1 object will be override and on flush only course 2 will be save to db.

Note: ALl the entities above with a transaction save by entity manager are saved to persistence context
persistence context will keep track of different object and there changes

3. EntityManager with persist and update both


      Course course = new Course();
      course.setName("Testing");
      entityManager.persist(course);
   
      course.setName("Testing 2");

First hibernate sequence will run to find the next available id on persist()
Then course object will be created in memory with new id fetch from db
Then insert query will be fired
Then update query will be fired all in a single transaction (at the end of method)

Insertable False : Won't allow inserts, give error constraint violation
Updatable False : Won't allow updates on that column for entity, no error as update won't run

4. Named Queries are
   <ul>
   <li>compiled and validated at app start-up time
   <li>easier to maintain than string literals embedded in your code
   <li>HQL and native SQL queries can be used and replaced without code changes (no need to re-compile your code)

So, I think you should definitely prefer named queries over string literals in your code. When you need some kind of dynamic query creation at runtime you should take a look at the Hibernate Criteria API.

5. Native Queries : Performance & Database specific operation not provided by JPA & Mass Update
   While using native queries we are not using persistence context.
   
6. Relationship: Course Student Passport Review
   Course ----1-Many---- Student
   Course ----1-Many---- Review
   Student ----1-1---- Passport
