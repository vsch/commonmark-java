---
title: Emoji Extension Spec
author: Vladimir Schneider
version: 0.1
date: '2016-06-06'
license: '[CC-BY-SA 4.0](http://creativecommons.org/licenses/by-sa/4.0/)'
...

---

## Emoji

Converts :warning: to its emoji image

No spaces between markers

```````````````````````````````` example Emoji: 1
# some leading text 
more text :warning : more text
.
<h1>some leading text</h1>
<p>more text :warning : more text</p>
.
Document[0, 52]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 52]
    Text[21, 51] chars:[21, 51, "more  …  text"]
````````````````````````````````


No spaces between markers

```````````````````````````````` example Emoji: 2
# some leading text 
more text : warning: more text
.
<h1>some leading text</h1>
<p>more text : warning: more text</p>
.
Document[0, 52]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 52]
    Text[21, 51] chars:[21, 51, "more  …  text"]
````````````````````````````````


No spaces between markers

```````````````````````````````` example Emoji: 3
# some leading text 
more text :warning
: more text
.
<h1>some leading text</h1>
<p>more text :warning
: more text</p>
.
Document[0, 52]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 52]
    Text[21, 39] chars:[21, 39, "more  … rning"]
    SoftLineBreak[39, 40]
    Text[40, 51] chars:[40, 51, ": mor …  text"]
````````````````````````````````


No spaces between markers

```````````````````````````````` example Emoji: 4
# some leading text 
more text :
warning: more text
.
<h1>some leading text</h1>
<p>more text :
warning: more text</p>
.
Document[0, 52]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 52]
    Text[21, 32] chars:[21, 32, "more  … ext :"]
    SoftLineBreak[32, 33]
    Text[33, 51] chars:[33, 51, "warni …  text"]
````````````````````````````````


Converts :warning: to its emoji image

```````````````````````````````` example Emoji: 5
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p><img src="/img/warning.png" alt="emoji places:warning" height="20" width="20" align="absmiddle" /></p>
.
Document[0, 31]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 31]
    Emoji[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


Converts :warning: to its emoji image

```````````````````````````````` example(Emoji: 6) options(use-github)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p><img src="https://github.githubassets.com/images/icons/emoji/unicode/26a0.png?v7" alt="emoji places:warning" height="20" width="20" align="absmiddle" /></p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    Emoji[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


Converts :warning: to its emoji image

```````````````````````````````` example(Emoji: 7) options(prefer-github)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p><img src="https://github.githubassets.com/images/icons/emoji/unicode/26a0.png?v7" alt="emoji places:warning" height="20" width="20" align="absmiddle" /></p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    Emoji[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


Should use cheat sheet image

```````````````````````````````` example(Emoji: 8) options(prefer-github)
# some leading text 
:couplekiss:
.
<h1>some leading text</h1>
<p><img src="/img/couplekiss.png" alt="emoji people:couplekiss" height="20" width="20" align="absmiddle" /></p>
.
Document[0, 33]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 33]
    Emoji[21, 33] textOpen:[21, 22, ":"] text:[22, 32, "couplekiss"] textClose:[32, 33, ":"]
      Text[22, 32] chars:[22, 32, "couplekiss"]
````````````````````````````````


Should use github

```````````````````````````````` example(Emoji: 9) options(prefer-cheat)
# some leading text 
:basecamp:
.
<h1>some leading text</h1>
<p><img src="https://github.githubassets.com/images/icons/emoji/basecamp.png?v7" alt="emoji symbols:basecamp" height="20" width="20" align="absmiddle" /></p>
.
Document[0, 31]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 31]
    Emoji[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "basecamp"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "basecamp"]
````````````````````````````````


Should fail

```````````````````````````````` example(Emoji: 10) options(prefer-cheat, unicode-only)
# some leading text 
:basecamp:
.
<h1>some leading text</h1>
<p>:basecamp:</p>
.
Document[0, 31]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 31]
    Emoji[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "basecamp"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "basecamp"]
````````````````````````````````


Should use github

```````````````````````````````` example(Emoji: 11) options(prefer-cheat, unicode)
# some leading text 
:basecamp:
.
<h1>some leading text</h1>
<p><img src="https://github.githubassets.com/images/icons/emoji/basecamp.png?v7" alt="emoji symbols:basecamp" height="20" width="20" align="absmiddle" /></p>
.
Document[0, 31]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 31]
    Emoji[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "basecamp"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "basecamp"]
````````````````````````````````


Should fail

```````````````````````````````` example(Emoji: 12) options(unicode-only, prefer-github)
# some leading text 
:basecamp:
.
<h1>some leading text</h1>
<p>:basecamp:</p>
.
Document[0, 31]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 31]
    Emoji[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "basecamp"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "basecamp"]
````````````````````````````````


Converts :warning: to its emoji image

```````````````````````````````` example(Emoji: 13) options(unicode)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p>⚠</p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    Emoji[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


Converts :warning: to its emoji image

```````````````````````````````` example(Emoji: 14) options(use-github, unicode)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p>⚠</p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    Emoji[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


should fail

```````````````````````````````` example(Emoji: 15) options(use-github, unicode)
# some leading text 
:couplekiss:
.
<h1>some leading text</h1>
<p>💏</p>
.
Document[0, 33]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 33]
    Emoji[21, 33] textOpen:[21, 22, ":"] text:[22, 32, "couplekiss"] textClose:[32, 33, ":"]
      Text[22, 32] chars:[22, 32, "couplekiss"]
````````````````````````````````


Converts :warning: to its emoji image

```````````````````````````````` example(Emoji: 16) options(prefer-github, unicode)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p>⚠</p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    Emoji[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


Should use cheat sheet image

```````````````````````````````` example(Emoji: 17) options(prefer-github, unicode)
# some leading text 
:couplekiss:
.
<h1>some leading text</h1>
<p>💏</p>
.
Document[0, 33]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 33]
    Emoji[21, 33] textOpen:[21, 22, ":"] text:[22, 32, "couplekiss"] textClose:[32, 33, ":"]
      Text[22, 32] chars:[22, 32, "couplekiss"]
````````````````````````````````


Should be undefined

```````````````````````````````` example(Emoji: 18) options(use-github)
# some leading text 
:couplekiss:
.
<h1>some leading text</h1>
<p>:couplekiss:</p>
.
Document[0, 33]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 33]
    Emoji[21, 33] textOpen:[21, 22, ":"] text:[22, 32, "couplekiss"] textClose:[32, 33, ":"]
      Text[22, 32] chars:[22, 32, "couplekiss"]
````````````````````````````````


Should use cheat sheet image

```````````````````````````````` example(Emoji: 19) options(unicode)
# some leading text 
:couplekiss:
.
<h1>some leading text</h1>
<p>💏</p>
.
Document[0, 33]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 33]
    Emoji[21, 33] textOpen:[21, 22, ":"] text:[22, 32, "couplekiss"] textClose:[32, 33, ":"]
      Text[22, 32] chars:[22, 32, "couplekiss"]
````````````````````````````````


should be undefined

```````````````````````````````` example(Emoji: 20) options(use-github, unicode-only)
# some leading text 
:basecamp:
.
<h1>some leading text</h1>
<p>:basecamp:</p>
.
Document[0, 31]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 31]
    Emoji[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "basecamp"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "basecamp"]
````````````````````````````````


change size

```````````````````````````````` example(Emoji: 21) options(size)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p><img src="/img/warning.png" alt="emoji places:warning" height="40" width="40" align="absmiddle" /></p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    Emoji[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


no size

```````````````````````````````` example(Emoji: 22) options(no-size)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p><img src="/img/warning.png" alt="emoji places:warning" align="absmiddle" /></p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    Emoji[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


no align

```````````````````````````````` example(Emoji: 23) options(no-align)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p><img src="/img/warning.png" alt="emoji places:warning" height="20" width="20" /></p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    Emoji[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


Should work in links

```````````````````````````````` example Emoji: 24
# some leading text 
[:warning:](/url)
.
<h1>some leading text</h1>
<p><a href="/url"><img src="/img/warning.png" alt="emoji places:warning" height="20" width="20" align="absmiddle" /></a></p>
.
Document[0, 39]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 39]
    Link[21, 38] textOpen:[21, 22, "["] text:[22, 31, ":warning:"] textClose:[31, 32, "]"] linkOpen:[32, 33, "("] url:[33, 37, "/url"] pageRef:[33, 37, "/url"] linkClose:[37, 38, ")"]
      Emoji[22, 31] textOpen:[22, 23, ":"] text:[23, 30, "warning"] textClose:[30, 31, ":"]
        Text[23, 30] chars:[23, 30, "warning"]
````````````````````````````````


```````````````````````````````` example(Emoji: 25) options(use-github)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p><img src="https://github.githubassets.com/images/icons/emoji/unicode/26a0.png?v7" alt="emoji places:warning" height="20" width="20" align="absmiddle" /></p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    Emoji[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


```````````````````````````````` example(Emoji: 26) options(unicode)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p>⚠</p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    Emoji[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


```````````````````````````````` example(Emoji: 27) options(use-github, unicode)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p>⚠</p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    Emoji[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


Should work in links

```````````````````````````````` example Emoji: 28
# some leading text 
[:warning:](/url)
.
<h1>some leading text</h1>
<p><a href="/url"><img src="/img/warning.png" alt="emoji places:warning" height="20" width="20" align="absmiddle" /></a></p>
.
Document[0, 39]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 39]
    Link[21, 38] textOpen:[21, 22, "["] text:[22, 31, ":warning:"] textClose:[31, 32, "]"] linkOpen:[32, 33, "("] url:[33, 37, "/url"] pageRef:[33, 37, "/url"] linkClose:[37, 38, ")"]
      Emoji[22, 31] textOpen:[22, 23, ":"] text:[23, 30, "warning"] textClose:[30, 31, ":"]
        Text[23, 30] chars:[23, 30, "warning"]
````````````````````````````````


Can be known only to requested target

```````````````````````````````` example(Emoji: 29) options(use-github)
# some leading text 
:basecamp:
.
<h1>some leading text</h1>
<p><img src="https://github.githubassets.com/images/icons/emoji/basecamp.png?v7" alt="emoji symbols:basecamp" height="20" width="20" align="absmiddle" /></p>
.
Document[0, 31]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 31]
    Emoji[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "basecamp"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "basecamp"]
````````````````````````````````


Can be known only to requested target fallback from unicode

```````````````````````````````` example(Emoji: 30) options(use-github, unicode)
# some leading text 
:basecamp:
.
<h1>some leading text</h1>
<p><img src="https://github.githubassets.com/images/icons/emoji/basecamp.png?v7" alt="emoji symbols:basecamp" height="20" width="20" align="absmiddle" /></p>
.
Document[0, 31]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 31]
    Emoji[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "basecamp"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "basecamp"]
````````````````````````````````


Unknown shortcuts are converted to text

```````````````````````````````` example Emoji: 31
# some leading text 
:warnings:
.
<h1>some leading text</h1>
<p>:warnings:</p>
.
Document[0, 32]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 32]
    Emoji[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "warnings"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "warnings"]
````````````````````````````````


Unknown shortcuts are converted to text

```````````````````````````````` example Emoji: 32
# some leading text 
:basecamp:
.
<h1>some leading text</h1>
<p>:basecamp:</p>
.
Document[0, 32]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 32]
    Emoji[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "basecamp"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "basecamp"]
````````````````````````````````


Unknown shortcuts are converted to text

```````````````````````````````` example(Emoji: 33) options(unicode)
# some leading text 
:basecamp:
.
<h1>some leading text</h1>
<p>:basecamp:</p>
.
Document[0, 31]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 31]
    Emoji[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "basecamp"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "basecamp"]
````````````````````````````````


Unknown shortcuts are converted to text

```````````````````````````````` example(Emoji: 34) options(use-github)
# some leading text 
:warnings:
.
<h1>some leading text</h1>
<p>:warnings:</p>
.
Document[0, 31]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 31]
    Emoji[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "warnings"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "warnings"]
````````````````````````````````


Unknown shortcuts are converted to text

```````````````````````````````` example(Emoji: 35) options(use-github)
# some leading text 
:couplekiss:
.
<h1>some leading text</h1>
<p>:couplekiss:</p>
.
Document[0, 33]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 33]
    Emoji[21, 33] textOpen:[21, 22, ":"] text:[22, 32, "couplekiss"] textClose:[32, 33, ":"]
      Text[22, 32] chars:[22, 32, "couplekiss"]
````````````````````````````````


Unknown shortcuts are converted to text, fallback to non unicode

```````````````````````````````` example(Emoji: 36) options(unicode)
# some leading text 
:couplekiss:
.
<h1>some leading text</h1>
<p>💏</p>
.
Document[0, 33]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 33]
    Emoji[21, 33] textOpen:[21, 22, ":"] text:[22, 32, "couplekiss"] textClose:[32, 33, ":"]
      Text[22, 32] chars:[22, 32, "couplekiss"]
````````````````````````````````


Unknown shortcuts are converted to text

```````````````````````````````` example(Emoji: 37) options(unicode)
# some leading text 
:warnings:
.
<h1>some leading text</h1>
<p>:warnings:</p>
.
Document[0, 31]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 31]
    Emoji[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "warnings"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "warnings"]
````````````````````````````````


Unknown shortcuts are converted to text with inline emphasis parsing

```````````````````````````````` example Emoji: 38
# some leading text 
:**warnings**:
.
<h1>some leading text</h1>
<p>:<strong>warnings</strong>:</p>
.
Document[0, 36]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 36]
    Emoji[21, 35] textOpen:[21, 22, ":"] text:[22, 34, "**warnings**"] textClose:[34, 35, ":"]
      StrongEmphasis[22, 34] textOpen:[22, 24, "**"] text:[24, 32, "warnings"] textClose:[32, 34, "**"]
        Text[24, 32] chars:[24, 32, "warnings"]
````````````````````````````````


## Source Position Attribute

```````````````````````````````` example(Source Position Attribute: 1) options(src-pos)
# some leading text 
:warning:
.
<h1 md-pos="2-19">some leading text</h1>
<p md-pos="21-30"><img src="/img/warning.png" alt="emoji places:warning" height="20" width="20" align="absmiddle" /></p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    Emoji[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


```````````````````````````````` example(Source Position Attribute: 2) options(src-pos)
# some leading text 
[:warning:](/url)
.
<h1 md-pos="2-19">some leading text</h1>
<p md-pos="21-38"><a href="/url" md-pos="21-38"><img src="/img/warning.png" alt="emoji places:warning" height="20" width="20" align="absmiddle" /></a></p>
.
Document[0, 38]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 38]
    Link[21, 38] textOpen:[21, 22, "["] text:[22, 31, ":warning:"] textClose:[31, 32, "]"] linkOpen:[32, 33, "("] url:[33, 37, "/url"] pageRef:[33, 37, "/url"] linkClose:[37, 38, ")"]
      Emoji[22, 31] textOpen:[22, 23, ":"] text:[23, 30, "warning"] textClose:[30, 31, ":"]
        Text[23, 30] chars:[23, 30, "warning"]
````````````````````````````````


## Issue 168

#168, Text with colons is incorrectly interpreted as an invalid emoji shortcut

```````````````````````````````` example Issue 168: 1
На сервере выставлен пояс GMT 00:00. Оно всегда должно быть *"3:50 ночи"*, даже если
.
<p>На сервере выставлен пояс GMT 00:00. Оно всегда должно быть <em>&quot;3:50 ночи&quot;</em>, даже если</p>
.
Document[0, 85]
  Paragraph[0, 85]
    Text[0, 60] chars:[0, 60, "На се … быть "]
    Emphasis[60, 73] textOpen:[60, 61, "*"] text:[61, 72, "\"3:50 ночи\""] textClose:[72, 73, "*"]
      Text[61, 72] chars:[61, 72, "\"3:50 … ночи\""]
    Text[73, 84] chars:[73, 84, ", даж …  если"]
````````````````````````````````


## Issue 304

#304, EmojiExtension doesn't support all emoji listed at the Cheat Sheet

```````````````````````````````` example(Issue 304: 1) options(use-cheat)
:simple_smile:
.
<p><img src="/img/simple_smile.png" alt="emoji people:simple_smile" height="20" width="20" align="absmiddle" /></p>
.
Document[0, 14]
  Paragraph[0, 14]
    Emoji[0, 14] textOpen:[0, 1, ":"] text:[1, 13, "simple_smile"] textClose:[13, 14, ":"]
      Text[1, 13] chars:[1, 13, "simpl … smile"]
````````````````````````````````


## Issue, Unicode missing

```````````````````````````````` example(Issue, Unicode missing: 1) options(unicode-only)
:ant:
.
<p>🐜</p>
.
Document[0, 5]
  Paragraph[0, 5]
    Emoji[0, 5] textOpen:[0, 1, ":"] text:[1, 4, "ant"] textClose:[4, 5, ":"]
      Text[1, 4] chars:[1, 4, "ant"]
````````````````````````````````


