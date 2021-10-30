# PRPO projekt: EVCharging

Aplikacija za iskanje, rezerviranje in uporabo polnilnic za električne avtomobile.


## Podatkovni model

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
    +getStations() List~Station~
    +addStation(Station)
}

class Station~Entity~{
    -Integer id
    -String stationName
    -Time openTime
    -Time closeTime
    -Float price
    -Integer wattage
    -String adapterType
    -Location location
    +getId() Integer
    +setId(Integer)
    +getStationName() String
    +setStationName(String)
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
    +getXCoordinate() Float
    +setXCoordinate(Float)
    +getYCoordinate() Float
    +setYCoordinate(Float)
}

class Charge~Entity~{
    -Integer userId
    -Integer stationId
    -Time beginTime
    -Time endTime
    -Float price
}

class Reservation~Entity~{
    -Integer userId
    -Integer stationId
    -Time reservationTime
    +createReservation(Integer, Integer)
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
