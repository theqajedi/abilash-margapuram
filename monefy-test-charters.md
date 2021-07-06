#Table of Contents

  - [Monefy](#section-id-1)
    - [Types of tests](#section-id-7)
    - [Priority levels](#section-id-14)
    - [Test Charters](#section-id-20)
      - [Features](#section-id-25)
        - [Add a new expense](#section-id-27)
        - [Add a new income](#section-id-57)
        - [Check spending](#section-id-86)
        - [Search a record](#section-id-113)
        - [Add accounts](#section-id-136)
        - [Create Data backup](#section-id-164)
        - [Restore data](#section-id-189)
        - [Clear data](#section-id-214)
        - [Account transfer](#section-id-239)
        - [Account Synchronization](#section-id-241)
        - [Account(s) Selection for balance viewing on home screen](#section-id-243)
        - [Time interval selection for balance viewing on home screen](#section-id-245)
        - [Account transfer](#section-id-247)
        - [Balance modes](#section-id-249)
        - [General settings](#section-id-251)
          - [Theme](#section-id-253)
          - [Language](#section-id-255)
          - [Currency](#section-id-257)
          - [First day of the week](#section-id-259)
          - [First day of month](#section-id-261)
          - [Password protection](#section-id-263)
      - [Pro features](#section-id-265)
        - [Account Synchronization](#section-id-266)
        - [Add custom categories](#section-id-276)
        - [Manage Currencies](#section-id-286)
  - [Q & A](#section-id-288)
  


<div id='section-id-1'/>

## Monefy

Monefy is a Budget Manager and Expense Tracker mobile app available on Android and iOS.

Below you can find the exploratory test charters performed.

<div id='section-id-7'/>

### Types of tests

To make sure we have a common understanding of different types.
- Sanity tests: A sanity test is a basic test to quickly evaluate whether to determine if testing for the build should proceed or not.
- Smoke tests: These tests are to verify whether the important features are working and there are no showstoppers in the build.
- Regression tests: These test are to confirm that code change does not affect existing features.

<div id='section-id-14'/>

### Priority levels

- High: Business critical, need to pass. Should be part of sanity and smoke tests
- Medium: Depends on the business use case. Can be part of smoke or regression tests.
- Low: Can be part of nightly or regression tests.

<div id='section-id-20'/>

### Test Charters

The test charters are categorised into features (regular) and pro features.
The test charters are arranged within each category depending on the priority level.

<div id='section-id-25'/>

#### Features

<div id='section-id-27'/>

##### <u>Add a new expense </u>

Priority: High  

Explore: Adding a new expense  

With: Different expense types on a android app with mobile running on Android 10
  
To discover: Functionality and bugs  

Steps Breakdown:
- Open the app
- Click on expense
    - Can also click on the category type icon
- Select < date >
    - By default should be current date
- Select an < account > 
    - Cash or Card
- Enter < amount >
- Add a < note >
    - Not mandatory, is optional
- Choose a < category >
- Check if the expenses total increased by the < amount > in the home screen
- Check if the < category > split % changed in the home screen
- Exit the app  

Duration: Medium(15-20 mins)  

Observations: Able to perform all the steps without problems

<div id='section-id-57'/>

##### <u>Add a new income  </u>

Priority: High

Explore: Adding a new income

With: Different income types on a android app with mobile running on Android 10

To discover: Functionality and bugs

Steps Breakdown:
- Open the app
- Click on income
- Select < date >
   - By default should be current date
- Select an < account > 
   - Cash or Card
- Enter < amount >
- Add a < note >
   - Not mandatory, is optional
- Choose a < category >
- Check if the income total increased by the < amount > in the home screen
- Check if the < category > split % changed in the home screen
- Exit the app

Duration: Medium(15-20 mins)

Observations: Able to perform all the steps without problems

<div id='section-id-86'/>

##### <u>Check spending  </u>

Priority: High

Explore: Check spending

With: Different UI navigations on a android app with mobile running on Android 10

To discover: Functionality and bugs

Steps Breakdown:
- Open the app
- Click on balance on home screen
   - Can also click on individual category icon on home screen
- Should show the list of records based on category
   - Income categories on the top followed by expense categories
   - Sorted by the total amount of individual category
- Click on sort by date icon on the top right
- Should show records sorted by date
- Navigate back to the home screen by clicking back button
   - Can navigate back by clicking on the balance as well
- Exit the app

Duration: Medium(15-20 mins)

Observations: Able to perform all the steps without problems

<div id='section-id-113'/>

##### <u>Search a record  </u>

Priority: High/Medium

Explore: Searching a record

With: UI navigations on a android app with mobile running on Android 10

To discover: Functionality and bugs

Steps Breakdown:
- Open the app
- Click on search icon on the top bar
   - Type the search text < search_keyword >
   - Can also do voice search
- Should show the list of records based on search keyword
- Navigate back to the home screen by clicking back button
- Exit the app

Duration: Medium(15-20 mins)

Observations: Able to perform all the steps without problems

<div id='section-id-136'/>

##### <u>Add accounts  </u>

Priority: High/Medium

Explore: Add custom accounts

With: Using UI on a android app with mobile running on Android 10

To discover: Functionality and bugs

Steps Breakdown:
- Open the app
- Click on the overflow menu on top right corner
- Click on Accounts icon
- Click on the plus icon
    - Should open a new account creation screen
- Click on Name field and enter < account_name >
- Click on Initial account balance field and enter < initial_account_balance >
- Click on Initial balance date field and enter < initial_ balance_date >
- Select the account type < type_of_account >
- Click on add on tht top right corner
    - Should navigate back to home screen with message < account_added_message >
- Exit the app
    
Duration: Medium(15-20 mins)

Observations: Able to perform all the steps without problems

<div id='section-id-164'/>

##### <u>Create Data backup  </u>

Priority: Medium

Explore: Create data backup

With: UI navigations on a android app with mobile running on Android 10

To discover: Functionality and bugs

Steps Breakdown:
- Open the app
- Click on the overflow menu on top right corner
- Click on Settings icon
- Navigate to bottom of the list to Data Backup section
- Click on create data backup
    - File explorer should open
- Click on save
    - Edit file name (optional)
- Exit the app
    
Duration: Short(10 mins)

Observations: Able to perform all the steps without problems
    
<div id='section-id-189'/>

##### <u>Restore data  </u>

Priority: Medium

Explore: Restore data

With: UI navigations on a android app with mobile running on Android 10

To discover: Functionality and bugs

Steps Breakdown:
- Open the app
- Click on the overflow menu on top right corner
- Click on Settings icon
- Navigate to bottom of the list to Data Backup section
- Click on Restore data
    - File explorer should open
- Select the backup file
    - Message should be displayed < backup_restored_message >
- Exit the app
    
Duration: Short(10 mins)

Observations: Able to perform all the steps without problems
    
<div id='section-id-214'/>

##### <u>Clear data  </u>

Priority: Low

Explore: Clear data

With: UI navigations on a android app with mobile running on Android 10

To discover: Functionality and bugs

Steps Breakdown:
- Open the app
- Click on the overflow menu on top right corner
- Click on Settings icon
- Navigate to bottom of the list to Data Backup section
- Click on Clear data
    - Popup confirmation dialog should appear
- Click on Yes.
    - Should navigate back to home screen with 0 balance.
- Exit the app
    
Duration: Short(10 mins)

Observations: Able to perform all the steps without problems
    
<div id='section-id-239'/>

##### <u>Account transfer  </u>

<div id='section-id-241'/>

##### <u>Account Synchronization   </u>

<div id='section-id-243'/>

##### <u>Account(s) Selection for balance viewing on home screen   </u>

<div id='section-id-245'/>

##### <u>Time interval selection for balance viewing on home screen   </u>

<div id='section-id-247'/>

##### <u>Account transfer </u>

<div id='section-id-249'/>

##### <u>Balance modes </u>

<div id='section-id-251'/>

##### <u>General settings </u>

<div id='section-id-253'/>

###### <u> Theme </u>

<div id='section-id-255'/>

###### <u> Language </u>

<div id='section-id-257'/>

###### <u>Currency </u>

<div id='section-id-259'/>

###### <u>First day of the week </u>

<div id='section-id-261'/>

###### <u>First day of month </u>

<div id='section-id-263'/>

###### <u>Password protection </u>

<div id='section-id-265'/>

#### Pro features </u>
<div id='section-id-266'/>

##### <u>Account Synchronization  </u>

Priority: High

Explore: Account Synchronization

With: Using Google Drive using UI on a android app with mobile running on Android 10

To discover: Functionality and bugs

<div id='section-id-276'/>

##### <u>Add custom categories </u>

Priority: High

Explore: Add custom categories

With: Using UI on a android app with mobile running on Android 10

To discover: Functionality and bugs

<div id='section-id-286'/>

##### <u>Manage Currencies </u>

Priority: Medium

Explore: Manage Currencies

With: Using UI on a android app with mobile running on Android 10

To discover: Functionality and bugs

<div id='section-id-288'/>

##Q & A
1. Findings from your charters. Did everything work as expected? What bugs were discovered?  
Ans: Couldn't complete testing the complete feature list, but out of the tested features, found no bugs.

1.  Prioritisation of those charters - which area of the app or testing would you explore first and why?  
Ans: Have sorted the test charters based on priority. And the higher priority features were tested first. reason being, if the main business cases are not working, then it affects a larger customer base and hence these should be tackled first.

1.  How much time you have planned for each charter?  
Ans: Have specified time spent for each charter. Mainly depending on the feature priority and permutation and combination of test cases to cover for each feature.

1.  What kind of risks you need to mitigate for this type of application?  
Ans: Since its a mobile app, the main risk is the app support for different android versions. So need to first check for agreed upon supported android versions and make sure the app is not crashing on them.  
Second is if the sanity tests are passing i.e making sure that the most basic business critical features are working fine.  
Third is non functional requirements to be met, ex. availability in app store, downloading, installation, security etc.

Note: I have completely skipped the testing of non functional cases like security, ease of navigation on UI, android versions support etc.
