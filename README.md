# Multiplayer Tank Battle Game

This is a real-time multiplayer tank battle game developed using the Greenfoot framework for the client-side interface. The game allows multiple players to engage in a battle, where they control tanks and try to eliminate each other to be the last one standing.

![Gameplay screenshot 1](/Gameplay1.png)
![Gameplay screenshot 2](/Gameplay2.png)

## Features
- Built a UDP server to manage the communication of packets between players to ensure synchronous gameplay
- Created a matchmaking system for players, allowing them to join games with other players of similar skill levels and preferences
- Employed Spring Boot to manage the game's player accounts and statistics
- Used a MySQL server to store and maintain player data, including account information and gameplay statistics

## Gameplay
The game allows players to control their tanks and use various weapons to destroy their opponents. The server is responsible for handling the transfer of player actions, such as shooting, moving, and reloading, between the clients to ensure synchronous gameplay.

## Statistics and Leaderboards
Players can create accounts, log in, and track their gameplay statistics, such as the number of games played, won, and lost, and their kill-death ratio. The MySQL server maintains player data, enabling the generation of leaderboards and other game-related analytics.

## Demo
Check out a short video demo of the game [here](https://drive.google.com/file/d/1ex1-Cvdrp8xQuycrHpmtsoV6sxYB4KD-/view?usp=share_link).

## Future Improvements
Future improvements to the game could include additional weapons, maps, and game modes. Additionally, enhancements to the matchmaking system could allow for more customized and personalized game experiences.
