# Flappy Bird

## Current Features

## Harel Alejandro Olguín Gaytán & Jorge Francisco Arriaga Escamilla

The game currently has multiple features to offer, and though it's still in development, a lot of advance has been done since the start of the project.

- **Bird & Game States:**
  The game states can be driven by the bird states, this means that, for example, if the bird has died, the game is aware of that and changes its state to game stopped or game over. This is very important because, depending on the game state, a lot of things can happen, a lot of things are going to be rendered and updated an a lot of other things won't, so the game needs to decide its state based on the bird's.
- **Dynamic Rendering:**
  This is a nice way to say that, based on the states, rendering and updating components of the game is going to be very different based on certain parameters, most important being, the state of the bird. For example, if the bird has died, neither the tubes nor the ground should continue moving, or when the game has not started, the bird should be updated on the default position until the game starts.

There are multiple features, some of the missing ones are.

- Bird collide with the tubes
- Proper score functionality
- Bird falling and jumping animation

However this things are still in heavy development and are expected to be finished soon with its proper code documentation. As for now, this is the preview of the game at its current state.

![Current Preview](Markdown/current.gif)
