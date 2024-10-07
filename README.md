# Currency Conversion Application

This is a Java-based application that converts currency values using real-time data from the ExchangeRate API. It allows users to input an amount, select the currency to convert from, and the currency to convert to, and it returns the converted value. The application handles errors and logs the process steps.

## Features

- Real-time currency conversion using ExchangeRate API.
- Handles multiple currencies based on user input.
- Error handling for invalid inputs or failed API requests.
- Logs conversion details and any issues encountered.

## How It Works

1. The user is prompted to input the currency they want to convert from.
2. The user then selects the currency they want to convert to.
3. The user inputs the amount they wish to convert.
4. The application fetches the latest conversion rates from the ExchangeRate API and performs the conversion.
5. The result is displayed to the user.

## Classes

### Main Class

- Handles user interaction and input.
- Calls the `CurrencyQuery` class to perform the currency conversion.

### CurrencyQuery Class

- Manages the connection to the ExchangeRate API.
- Sends HTTP requests and parses the API response using Gson.
- Performs the conversion calculation and returns the converted value.
- Logs important actions and handles errors like invalid parameters or failed API requests.

### ConversionData Record

- A simple Java record to hold the currency conversion rates from the API response.

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/FelipeBert/currency-converter.git
