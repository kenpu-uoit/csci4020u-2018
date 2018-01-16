# Lab 1: regular expression based parsing

NSERC releases successful research grant applications online.
You can see the result [here](http://www.nserc-crsng.gc.ca/NSERC-CRSNG/FundingDecisions-DecisionsFinancement/ResearchGrants-SubventionsDeRecherche/ResultsGSC-ResultatsCSS_eng.asp?Year=2017)

The result for Computer Science is given in the file `nserc-cs-2017.html`.

## Modeling

- View the HTML page

- For each of the following information, examine the HTML page source, and
identify the context that the information appears.

    - Researcher name
    - Department
    - Institution
    - Title
    - Number of years (Term)
    - Awarded amount

## Regular expression

For each pieces of information, author the regular expression to match the
context, and the group to capture the information content.


## Parser

Write a Java program to output the information, and use the data to compute the
following:

- Which university has the most number of successful applications?

- Which university has the most funding allocated?

- What is the total funding allocated?


