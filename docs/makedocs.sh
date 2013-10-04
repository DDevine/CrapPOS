#!/bin/bash
# You need to install graphvis, javadoc and umlgraph
javadoc -doclet org.umlgraph.doclet.UmlGraphDoc -docletpath /usr/share/java/umlgraph.jar -private -attributes -classpath -types -all ../src ../src/au/edu/griffith/ict/*.java
