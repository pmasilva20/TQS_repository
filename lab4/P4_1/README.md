Name:Pedro Silva
NºMec:93011

a) Identify a couple of examples on the use of AssertJ expressive methods chaining.
Some of the examples found in the test cases:
assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail());
assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);     assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());

b) Identify an example in which you mock the behavior of the repository (and avoid involving a
database).

Various examples can be found in the xx class, as we can observe in the setUp() method where Mockito is used to mock the repository class for various methods:
Ex:        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);

c) What is the difference between standard @Mock and @MockBean?

They are both used with the same function, make a mock object that
can be used as a stub to return values for methods or verify if a said method was called, but while @Mock is used in test classes, @MockBean will add said object to Spring's application context and will be managed by Spring's IoC contaner. 


d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be
used?

It contains configurations of various components, in this case, it configures values related to the Database used, these values will be used to autoconfigure the database when used. 


