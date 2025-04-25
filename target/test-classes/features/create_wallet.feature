Feature: Create Wallet Functionality

  Background:
    Given Trust Wallet application is opened

  Scenario: Create Wallet Successfully

    When User clicks on "Create New Wallet" button
    And "Create passcode" page is displayed
    And User enters the passcode
    And "Confirm passcode" page is displayed
    And User confirms the passcode
    And Notifications pop-up appears and user taps the skip button
    And User skips crypto deposit screen
    And User navigates to Wallets page
    Then User verifies the wallet name on the Wallet page
