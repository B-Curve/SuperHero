create table Organization(
OrganizationID int primary key auto_increment,
`Name` varchar(255) not null,
Description text null,
Address text null,
Phone varchar(18) null
);

create table `Type`(
TypeID int primary key auto_increment,
Title varchar(12) not null
);

create table `Super`(
SuperID int primary key auto_increment,
`Name` varchar(255) not null,
Description text null,
TypeID int not null,
foreign key (TypeID) references `Type`(TypeID)
);

create table Locations(
LocationID int primary key auto_increment,
Description text null,
Address text null,
Latitude decimal(9,6) not null,
Longitude decimal(9,6) not null
);

create table SuperLocation(
LocationID int not null,
SuperID int not null,
foreign key (LocationID) references Locations(LocationID),
foreign key (SuperID) references Super(SuperID),
primary key (LocationID,SuperID)
);

create table SuperOrganization(
SuperID int not null,
OrganizationID int not null,
foreign key (SuperID) references Super(SuperID),
foreign key (OrganizationID) references Organization(OrganizationID),
primary key (SuperID,OrganizationID)
);

create table SuperPowers(
SuperPowerID int primary key auto_increment,
Title varchar(255) not null,
Description text null
);

create table Sightings(
SightingID int primary key auto_increment,
LocationID int not null,
Description text null,
Headline varchar(255) not null,
`Date` date not null,
foreign key (LocationID) references Locations(LocationID)
);

create table SuperSightings(
SuperID int not null,
SightingID int not null,
foreign key (SuperID) references Super(SuperID),
foreign key (SightingID) references Sightings(SightingID),
primary key (SuperID,SightingID)
);

create table SuperSuperPowers(
SuperID int not null,
SuperPowerID int not null,
foreign key (SuperID) references Super(SuperID),
foreign key (SuperPowerID) references SuperPowers(SuperPowerID),
primary key (SuperID,SuperPowerID)
);