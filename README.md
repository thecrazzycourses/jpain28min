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


