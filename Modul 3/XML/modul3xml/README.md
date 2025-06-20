# Roblox Games List App

A simple Android application that displays a list of popular Roblox games using modern Android development practices.

## Features

- RecyclerView implementation with custom adapter
- ViewBinding for view access
- Navigation Component for fragment navigation
- Material Design components
- Responsive layout (supports both portrait and landscape)
- Rounded corners for images and list items
- Explicit intents for external links
- Single Activity architecture with multiple fragments

## Project Structure

- `MainActivity`: Single activity that hosts the NavHostFragment
- `RobloxGameListFragment`: Displays the list of Roblox games
- `RobloxGameDetailFragment`: Shows details for a selected game
- `RobloxGameAdapter`: RecyclerView adapter for the games list
- `RobloxGame`: Data class representing a Roblox game

## Implementation Details

- Uses ViewBinding for safe view access
- Implements Navigation Component for fragment navigation
- Uses MaterialCardView for rounded corners
- Handles configuration changes (rotation) properly
- Implements explicit intents for external links
- Uses Safe Args for type-safe navigation

## Requirements

- Android Studio Arctic Fox or newer
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)
- Kotlin version: 1.9.0

## Dependencies

- AndroidX Core KTX
- AppCompat
- Material Design Components
- ConstraintLayout
- Navigation Component
- ViewBinding 