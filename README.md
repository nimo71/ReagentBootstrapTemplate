# Template

FIXME: Write a one-line description of your library/project.

## Overview

FIXME: Write a paragraph about the library/project and highlight its goals.

Using stuart sierra component for reloadable code... [explaination](
http://stackoverflow.com/questions/29070883/how-to-use-stuart-sierras-component-library-in-clojure)


## Setup

To run: 

    rlwrap lein run -m clojure.main

then from the REPL: 

    user=> (reset)

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

To clean all compiled files:

    lein clean

To create a production build run: TODO....

    lein do clean, cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL. 

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
