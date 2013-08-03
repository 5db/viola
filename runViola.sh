#!/bin/sh

# Sets GROOVY_HOME in CLASSPATH
export GROOVY_HOME=/Users/jsingh/dev/sdks/groovy-2.1.6
export PATH=$PATH:$GROOVY_HOME/bin

# Directory where your script resides
cd /Users/jsingh/dev/workspaces/scripts/viola

# Runs Viola script
groovy Viola.groovy