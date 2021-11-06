# PRPO projekt: EVCharging

Aplikacija za iskanje, rezerviranje in uporabo polnilnic za električne avtomobile.


## Podatkovni model

![Razredni diagram](res/class-diagram.svg)

Koda za podatkovni model:

```mermaid
classDiagram

class Entity{
    -Integer id
    +getId() Integer
    +setId(Integer)
}

class Person~Entity~{
    -Integer id
    -String name
    -String surname
    -String email
    +getId() Integer
    +setId(Integer)
    +getName() String
    +setName(String)
    +getSurname() String
    +setSurname(String)
    +getEmail() String
    +setEmail(String)
}

class User~Person~{
    -Integer id
    -String name
    -String surname
    -String email
    +getId() Integer
    +setId(Integer)
    +getName() String
    +setName(String)
    +getSurname() String
    +setSurname(String)
    +getEmail() String
    +setEmail(String)
    -List~Charge~ charges
    -List~Reservation~ reservations
    +getCharges() List~Charge~
    +addCharge(Charge)
    +getReservations() List~Reservation~
    +addReservation(Reservation)
}

class Owner~Entity~{
    -Integer id
    -String name
    -String surname
    -String email
    +getId() Integer
    +setId(Integer)
    +getName() String
    +setName(String)
    +getSurname() String
    +setSurname(String)
    +getEmail() String
    +setEmail(String)
    -List~Station~ ownedStations
    +getOwnedStations() List~Station~
    +addOwnedStation(Station)
}

class Station~Entity~{
    -Integer id
    -String stationName
    -Owner owner
    -Time openTime
    -Time closeTime
    -Float price
    -Integer wattage
    -String adapterType
    -StationLocation location
    +getId() Integer
    +setId(Integer)
    +getStationName() String
    +setStationName(String)
    +getOwner() Owner
    +setOwner(Owner)
    +getOpenTime() Time
    +setOpenTime(Time)
    +getCloseTime() Time
    +setCloseTime(Time)
    +getPrice() Float
    +setPrice(Float)
    +getWattage() Integer
    +setWattage(Integer)
    +getAdapterType() String
    +setAdapterType(String)
    +getStationLocation() StationLocation
    +setStationLocation(StationLocation)
}

class City~Entity~{
    -Integer id
    -String name
    +getId() Integer
    +setId(Integer)
    +getName() String
    +setName(String)
}

class StationLocation~Entity~{
    -Integer id
    -City city
    -String address
    -Float xCoordinate
    -Float yCoordinate
    +getId() Integer
    +setId(Integer)
    +getCity() City
    +setCity(City)
    +getAddress() String
    +setAddress(String)
    +getXCoordinate() Float
    +setXCoordinate(Float)
    +getYCoordinate() Float
    +setYCoordinate(Float)
}

class Charge~Entity~{
    -User user
    -Station station
    -Time beginTime
    -Time endTime
    -Float price
    +getUser() User
    +setUser(User)
    +getStation() Station
    +setStation(Station)
    +getBeginTime() Time
    +setBeginTime(Time)
    +getEndTime() Time
    +setEndTime(Time)
    +getPrice() Float
    +setPrice(Float)
}

class Reservation~Entity~{
    -User user
    -Station station
    -Time reservationTime
    +getUser() User
    +setUser(User)
    +getStation() Station
    +setStation(Station)
    +getReservationTime() Time
    +setReservationTime(Time)
}

Entity<|--Person: Extends
Person<|--User: Extends
Person<|--Owner: Extends
Entity<|--Station : Extends
Entity<|--City : Extends
Entity<|--StationLocation : Extends
Entity<|--Charge : Extends
Entity<|--Reservation : Extends
Owner "1" --> "*" Station: Owns
User "1" --> "*" Charge
Charge "*" --> "1" Station
User "1" --> "*" Reservation
Reservation "*" --> "1" Station
City "1" <-- "*" StationLocation
StationLocation "1" <-- "*" Station
```


## Licenca

Avtorja projekta sta Ana Strmčnik in Erazem Kokot.
Projekt je licenciran z [MIT licenco](LICENSE).
