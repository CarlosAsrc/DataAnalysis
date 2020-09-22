[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=CarlosAsrc_DataAnalysis&metric=alert_status)](https://sonarcloud.io/dashboard?id=CarlosAsrc_DataAnalysis)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=CarlosAsrc_DataAnalysis&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=CarlosAsrc_DataAnalysis)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=CarlosAsrc_DataAnalysis&metric=security_rating)](https://sonarcloud.io/dashboard?id=CarlosAsrc_DataAnalysis)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=CarlosAsrc_DataAnalysis&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=CarlosAsrc_DataAnalysis)


[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=CarlosAsrc_DataAnalysis&metric=coverage)](https://sonarcloud.io/dashboard?id=CarlosAsrc_DataAnalysis)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=CarlosAsrc_DataAnalysis&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=CarlosAsrc_DataAnalysis)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=CarlosAsrc_DataAnalysis&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=CarlosAsrc_DataAnalysis)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=CarlosAsrc_DataAnalysis&metric=bugs)](https://sonarcloud.io/dashboard?id=CarlosAsrc_DataAnalysis)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=CarlosAsrc_DataAnalysis&metric=code_smells)](https://sonarcloud.io/dashboard?id=CarlosAsrc_DataAnalysis)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=CarlosAsrc_DataAnalysis&metric=ncloc)](https://sonarcloud.io/dashboard?id=CarlosAsrc_DataAnalysis)

# Challange

A data analysis system that import lots of flat files, read, analyse the data, and output a report. The analysis must be executed every changes in the input directory.

### Flat input file layout
There are 3 kinds of data inside those files. For each kind of data there is a different layout.

 - Salesman
    - 001çCPFçNameçSalary

 - Customer
    - 002çCNPJçNameçBusiness Area
 - Sales
    - 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name

The input file must be found in `%HOMEPATH%/data/in`

### Flat output file data
 - Amount of clients in the input file
 - Amount of salesman in the input file
 - ID of the most expensive sale
 - Worst salesman ever

The output file must be found in `%HOMEPATH%/data/out`

 ## Implementation
 In special, this implementation use Singleton Design Pattern, DAO Pattern for data file persistence, and Java WatchService to monitoring and searching for files changes to refresh the output file. The system also use multithreading to offer a terminal interaction while the WatchService works.
