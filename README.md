# Moderna: Currency Calculator
A proof-of-concept app that calculates currency conversions live using data from the European Central Bank

## What this is

This is simply a currency rate calculator. It does NOT convert actual money; it will tell you how much of one currency is valued compared to another currency.
The rates are real rates, provided by the European Central Bank using https://exchangeratesapi.io/ to fetch the data.

## Development Topics

This app uses the following features: 
  - MotionLayout: for a modern look with animations
  - Retrofit: Square REST api used to fetch currency rate data
  - MVVM Architechture: ViewModels are used to structure the app
  - Kotlin
