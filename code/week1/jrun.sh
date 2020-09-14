#!/bin/bash

javac src/algorithms/SocialNetwork.java 
echo '
===========================
SocialNetwork.java compiled 
===========================
'
javac src/runTest.java
echo '
======================
runTest.java compiled
======================
'
java src.runTest

