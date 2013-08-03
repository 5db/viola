# Viola
A simple Groovy (uses Java 7 APIs) script to keep overly used directories always cleaned.
Viola works by creating a `viola` directory in `$USER_HOME` where it archives content of 
every directory specified in `viola.properties` file. To put Viola to best use it is 
advised to run it through a CRON job. More information on how to configure it via CRON is
provided below.

## Archive Structure
For example let's say you want to archive content of `temp` directory which is located at
`\home\peter\dev\temp`. Then Viola will create the following directory structure:

`\home\peter\viola\tempArchives\temp-DATE_TIME`

# How to configure Viola?

* Download and extract Viola project
* Edit runViola.sh to set GROOVY_HOME and absolute path to where you extracted Viola
* Edit viola.properties to specify comma separated folder(s) you would like Viola to clean

## Configure CRON Job

* Open Terminal and enter `crontab -e`. Edit file to put the following job:

`0 20 /7 * * ABSOLUTE_PATH_TO_runViola.sh -update -config=myconfig`

The above CRON job is configured to run every 7 days at 9pm. You can configure this as
per your requirements.

Help with CRON Arguments:

Argument 1: Minute (0 - 59)
Argument 2: Hour (0 - 23)
Argument 3: Day of Month (1 - 31)
Argument 4: Month (1-12)
Argument 5: Day of Week (0 - 6) Sunday = 0
Argument 6: Command

# Current Limitations
* Cannot clean folders that require ADMIN/ROOT access

# License
Copyright (c) 2013 Jatinder Singh

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.