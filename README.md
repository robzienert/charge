Charge
======

*WIP*: Scalability test automation suite.

Design
------

Simple scalability test development with Spock and Guice. Adding support for different tooling is done through the use
of Drivers (Gatling Driver, JMeter Driver, etc), added into Guice. Further, test data can optionally be setup through
templated fixtures, which are added to the FixtureService: Developers can select a common dataset and input runtime
configuration to adjust the template to their needs - or generators can be directly invoked or extended if necessary.

Usage
-----

To run only the scalability tests:

```
$ gradle charge-tests:test
```

TODO
----

* Test data fixtures (completely hardcoded now)
* Concurrent driver executions (GPars, Guava Futures? Akka?)
* Selenium integration
* Better Gatling simulation runtime configuration
* Better Gatling integration
    * Enhanced configuration
    * "Simulation builder"? Scenarios are built and can be chained together at the test level; would require runtime
    compilation of simulations.
