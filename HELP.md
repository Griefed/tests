# Java versions

ServerPackCreator is develop with Java 8, ServerPackCreator is guaranteed to work with that version.

Users have reported the following Java versions to also work with ServerPackCreator:

1. Java 19 Pre-Release (reported by [Griefed](https://github.com/Griefed))
1. Java 18 (reported by [Kreezxil](https://github.com/Kreezxil))
1. Java 17 (reported by [Kreezxil](https://github.com/Kreezxil))
1. Java 16 (reported by [Griefed](https://github.com/Griefed))
1. Java 11 (reported by [Kreezxil](https://github.com/Kreezxil))

**ATTENTION:** I **can** not and **will** not guarantee that ServerPackCreator will work flawlessly
with any other Java version other than version 8. Things may break at **random**, at **any** place,
if a **different** Java version is used. You are welcome to try and use any other version, but do **
not** report issues back to me if you do encounter problems when running ServerPackCreator with a
more recent Java version.

# Fugly Artifacts

Depending on which JDK you are using, ServerPackCreator may display some really ugly artifacts and
anti-aliasing issues in the GUI. So far, I've nailed this issue down to occuring when
using [AdoptiumJDKs](https://adoptium.net/).

Tests have shown, that using the
official [Oracle JDKs](https://www.oracle.com/java/technologies/downloads/archive/), this problem
does not occur.

## Using Adoptium JDK Java 8 331

![fugly_artifacts](https://i.griefed.de/images/2022/07/01/fugly_artifacts.png)

## Using Oracle JDK Java 8 331

![a_ok](https://i.griefed.de/images/2022/07/01/a_ok.png)

# Tips, tricks and help

## Start Script Templates (3.8.x and up)

As of 3.8.0, ServerPackCreator creates start scripts based on templates.
Inside the `server_files`-directory you will find the two default templates:

1. `default_template.ps1`
2. `default_template.sh`

You have two options now:

1. Edit the existing default templates to your liking and saving them under a different name
2. Create your own templates or add additional ones for different file-endings

## Powershell

> **Paths with spaces**
> Powershell scripts by default can not be opened with a double-click if the path to said script
> contains spaces. If you wish to remedy this or want to read more about this behaviour, this article
> talks about it in great detail:
[Fix Problem Where Windows PowerShell Cannot Run Script Whose Path Contains Spaces](https://blog.danskingdom.com/fix-problem-where-windows-powershell-cannot-run-script-whose-path-contains-spaces/)<br>
> Keep in mind though that things may still break when working with paths with spaces in them. If
> things still break with a path with spaces, even after trying the fixes from the link above, then I
> suggest moving things to a folder whose path contains no spaces. I'm afraid there's nothing I can do
> about that.
> {.is-warning}

> **Running Powershell scripts**
> By default, running Powershell scripts from untrusted sources is probably disabled on your system.
> As such, you will not be able to run the `start.ps`-scripts just yet. You need to allow running
> unsigned scripts first. See https://superuser.com/a/106363 for a short explanation on how to
> enable/allow running unsigned scripts with Powershell.
> Bear in mind that this introduces a security risk on your system. After making the changes from the
> link above, you can run any Powershell script you like, and as such, introduce any and all security
> risk into your system. So, beware when running scripts from unknown sources.
> {.is-warning}

### Default values

ServerPackCreator always supplies a couple of default key-value-pairs during script creation.

| Key                               | Value                                                                                                                                                                                                                                                                                                    |
|-----------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| SPC_SERVERPACKCREATOR_VERSION_SPC | The version of ServerPackCreator with which the start script was created.                                                                                                                                                                                                                                |
| SPC_MINECRAFT_VERSION_SPC         | The Minecraft version of the modpack from which the server pack is created.                                                                                                                                                                                                                              |
| SPC_MODLOADER_SPC                 | The modloader of the modpack from which the server pack is created.                                                                                                                                                                                                                                      |
| SPC_MODLOADER_VERSION_SPC         | The modloader version of the modpack from which the server pack is created.                                                                                                                                                                                                                              |
| SPC_MINECRAFT_SERVER_URL_SPC      | The download URL corresponding to the Minecraft version of the modpack from which the server pack is created.                                                                                                                                                                                            |
| SPC_JAVA_ARGS_SPC                 | Java JVM args which are to be used when running the server pack.                                                                                                                                                                                                                                         |
| SPC_JAVA_SPC                      | `java` by default. Start scripts in ZIP-archives will always receive `java` as the value. You may change the <br/>value in the table provided in the GUI if you need a different Java path for local testing and debugging.<br/>Scripts in the unzipped server pack will have the custom value avaiable. |
| SPC_FABRIC_INSTALLER_VERSION_SPC  | The latest release version of the Fabric installer during the creation of the start scripts.                                                                                                                                                                                                             |
| SPC_QUILT_INSTALLER_VERSION_SPC   | The latest release version of the Quilt installer during the creation of the start scripts.                                                                                                                                                                                                              |

### Placeholders and values (3.14.x and up)

As of 3.14.x you can add and edit placeholders to your liking.
Make sure to map your desired value to the appropriate placeholder key.
The GUI provides a table where you can configure these, or any other values, to your liking.

The way this system works is that ServerPackCreator scans the given template for any occurance of a
given placeholder, say `SPC_JAVA_SPC`, and replaces that occurance in the template with the assigned
value, `java` by default unless you changed it.
After every configured placeholder has been replaced with the configured value, the resulting text
is written to the start scripts, based on the file-endings of your provided templates.
BAM! Template engine with customizable content!

> **Working with custom placeholders**
> When adding custom placeholders to your start script templates, make sure to write them in such a
> way that no other text may get replaced with the value by accident.
> Take the default placeholders for example. They have a pre- and suffix, `SPC_` and `_SPC`
> respectively. That way, no other text will get replaced by accident. If you
> were to use placeholders such as `JAVA` only, then bad stuff can and will happen. The default
> shell-script, for example, uses the variable name `JAVA` for Java-based operations. With the
> previously mentioned placeholder `JAVA`, that variable would be removed from the scripts,
> potentially rendering them useless. Nobody wants that, right? :D
> {.is-warning}

### Changing the default templates

Starting from versions **3.13.x** and up, the default templates are always overwritten during
startup of ServerPackCreator, to ensure any user always has the latest version of the default
templates available to them and to ensure any changes made to them, by me, end up in the users
hands.
This has the benefit of making any migration for future versions not your problem.

The downside: If you want to customize these templates, you will have to create separate templates
and set the property for the templates accordingly.

In other words:

1. Copy the desired template to a name of your liking, e.g. `custom-template.ps1`
   and `custom-template.sh`
2. Make your changes to these custom templates
3. Set `de.griefed.serverpackcreator.serverpack.script.template`
   to `custom-template.ps1,custom-template.sh`
4. ...
5. Profit

Now the default templates will not be used to create start scripts for your server packs, but
instead, your custom-templates will be used.

### Adding/removing templates

1. The file-endings of the templates determine the file ending of the start script (`template.bat`
   becomes `start.bat`, `template.sh` becomes `start.sh` and so on)
1. Put your template(s) into the `server_files`-directory
1. Edit the `serverpackcreator.properties`-file and change
   the `de.griefed.serverpackcreator.serverpack.script.template`-property accordingly

**Example:**
Say you've written a very nice template for some `.bat`
-scripts, `my-awesome-batch-script-template.bat`, and want ServerPackCreator to use said template
during the script creation.
In that case you would need to put said template into the `server_files`-directory,
set `de.griefed.serverpackcreator.serverpack.script.template`
to `my-awesome-batch-script-template.bat` so it looks
like `de.griefed.serverpackcreator.serverpack.script.template=my-awesome-batch-script-template.bat`
and you've good to go.

When you now generate a server pack, a `start.bat`-script will be created based on
your `my-awesome-batch-script-template.bat`-template.

So, at the end, after writing your template and adding it to your ServerPackCreator configuration,
your folders and config should look/contain like the following:

- **ServerPackCreator
  Properties:** `de.griefed.serverpackcreator.serverpack.script.template=default_template.ps1,default_template.sh,my-awesome-batch-script-template.bat`
- **server_files- folder should contain:**
    - default_template.ps1
    - default_template.sh
    - my-awesome-batch-script-template.bat
    - [...]

This will result in ServerPackCreator creating three scripts: `start.bat`, `start.ps1`, `start.sh`

> **Encoding**
> When writing custom templates, you must store them with **UTF-8** encoding. That is the encoding in
> which ServerPackCreator reads them. So in order for control characters or any other special
> characters to work as you expect them to, make sure to encode your custom templates in **UTF-8**!
> {.is-warning}

## Automatically updating script Java placeholder (3.14.x and up)

As of ServerPackCreator version 3.14.x you can define properties which, when configured correctly,
will set the `SPC_JAVA_SPC`-placeholder in the script variables depending on which Java version is
required by Minecraft.

The two property in question:

- `de.griefed.serverpackcreator.script.javaN`

Where `N` represents the version of the Java installation you want to make available to
ServerPackCreator for script placeholder editing.

Set any of these to the exact path to your correlating Java executable or binary file.

Examples:

- `de.griefed.serverpackcreator.script.java8=C\:/Program Files/Java/jdk1.8.0_331/bin/java.exe`
- `de.griefed.serverpackcreator.script.java17=C\:/Program Files/Java/jdk-17.0.3.1/bin/java.exe`

Now, when you select your Minecraft version in your GUI, or load an already existing configuration,
ServerPackCreator will set the value for the `SPC_JAVA_SPC`-placeholder to either
`C\:/Program Files/Java/jdk1.8.0_331/bin/java.exe` or
`C\:/Program Files/Java/jdk-17.0.3.1/bin/java.exe`,
depending on whether the Minecraft version requires Java 8 or Java 17.

The `SPC_JAVA_SPC`-placeholder in the scripts in the ZIP-archives, should you choose to let
ServerPackCreator create one, will still be set to `java`.

### Deactivating the automatic updating of the Java placeholder

It may not be desired by everyone to always automatically update the Java placeholder in the script
variables.
If you don't want ServerPackCreator to do so, set the following property in
your `serverpackcreator.properties` to `false`

`de.griefed.serverpackcreator.script.java.autoupdate`

This will disable the automatic updating of the `SPC_JAVA_SPC`-placeholder in your script variables.

## Clientside-only modslist

### Updating

If you wish to use the latest and greatest hits of clientside-only mods ServerPackCreator has to
offer, you need to update it via **Menu Bar -> File -> Update Fallback Clientside Modslist** and
then either

- Clear your clientside-only mods list in your config, save it, then load it again (either via the
  menu or by restarting SPC)

**OR**

- Generate a new configuration and migrate your old config to it

**OR**

- Generate a new configuration and copy the list to your old config manually

**OR**

- Empty the clientside-mods field, save the configuration, load the configuration. This will fill it
  with the default clientside-mods list

If you added custom mods to this list, you must make sure you migrate them over to the new setting
yourself.

### Filter methods (3.12.x and up)

You can change the behaviour with which ServerPackCreator filters the mods with the list of
clientside-only modslist you specify.
By default, ServerPackCreator will check each entry in that list against the name of every mod-JAR
in your modpacks mods-directory and see whether said JAR starts with said entry.

Example:

- JAR: BetterPingDisplay-1.16.5-1.0.jar
- Entry: BetterPingDisplay-

The mod will be excluded, because its name starts with `BetterPingDisplay-`.

This behaviour can be changed by
editing `de.griefed.serverpackcreator.serverpack.autodiscovery.filter=START`
in `serverpackcreator.properties`.

There are five possible settings which affect the way these filters happen:

1. **`START`**

    - Default setting. Checks whether the name of a mod *starts* with a given entry.

2. **`END`**

    - Checks whether the name of a mod *ends* with a given entry.

3. **`CONTAIN`**

    - Checks whether the name of a mod *contains* a given entry.

4. **`REGEX`**

    - Performs a regex-match of the name of a mod against a given entry.

5. **`EITHER`**

    - Not recommended unless you know **exactly** what you are doing. If used carelessly, this can
      result in all mods or unwanted mods being excluded. Checks whether any of the above result in
      a positive match.

## FancyMenu

Starting from version **2.12.1** FancyMenu can be run on servers, too.
If you use **2.12.1** or newer and you want to include it in your server pack, you need to:

1. Remove the `fancymenu_`-entry from your list of clientside-only mods-list.
2. To be on the safe side, add an entry to your file/directories to include in your server pack for
   your version of FancyMenu:
    -
    Example: `mods/fancymenu_forge_2.12.1_MC_1.19-1.19.1.jar;mods/fancymenu_forge_2.12.1_MC_1.19-1.19.1.jar`

## Automatic detection of clientside-only mods

ServerPackCreator can try and automatically determine whether a mod is clientside-only or not. This
feature is enabled by default. In order to deactive/activate it manually,
set `de.griefed.serverpackcreator.serverpack.autodiscoverenabled` to `true` (enable) or `false` (
disable).

Keep in mind: This feature is not 100% foolproof. It can not and will not detect every
clientside-only mod due to the diverse nature of Minecraft mods and the way their creators declare
sideness or dependencies.

If you encounter a mod that did not get caught by the autodetection and is not yet present in the
fallback-list, please create
an [improvement request on GitHub](https://github.com/Griefed/ServerPackCreator/issues/new?assignees=Griefed&labels=enhancement&template=improvement.yml&title=%5BImprovement+request%5D%3A+)
with the title being similar to

> [Improvement request]: Add mod-x, mod-y and mod-z to the fallback-list

In the **Whats the feature you would like to be improved?**-section, something along the lines of

> I would like the following mods to be added to the fallback-modslist
> - mod-x (Link to CurseForge project)
> - mod-y (Link to CurseForge project)
> - mod-z (Link to CurseForge project)

## JVM flags/args

Say you've got a couple of extra flags or arguments you want your server to start wich. Maybe
allocate more RAM? Use Aikars flags?

Right next to the Java Args textarea is a button which will set your args
to [Aikars Flags](https://aikar.co/mcflags.html). If you've already entered something into said
textarea, ServerPackCreator will ask you whether you want to overwrite the current settings,
replacing them with Aikars flags.

Using said button will set the textarea to:

> -Xms4G -Xmx4G -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:
> +UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=30 -XX:
> G1MaxNewSizePercent=40 -XX:G1HeapRegionSize=8M -XX:G1ReservePercent=20 -XX:G1HeapWastePercent=5 -XX:
> G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=15 -XX:G1MixedGCLiveThresholdPercent=90
> -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:
> MaxTenuringThreshold=1 -Dusing.aikars.flags=https://mcflags.emc.gs -Daikars.new.flags=true

These will be saved to the start scripts which ServerPackCreator will create during the server pack
generation.

If you wish to change these flags globally, you will need to edit
your `serverpackcreator.properties`-file and
change `de.griefed.serverpackcreator.configuration.aikar` to your liking.

**Note:** This is not a pack-by-pack configuration, but global. Meaning: Changing this setting and
using the appropriate button in the GUI will result in the same setting for all server pack
configurations.

## Excluding files/folders

ServerPackCreator allows you to exclude files, directories and / or file-types based on simple
expressions. The way it does that is by checking every file about to be copied to the server pack
for whether it contains any of the expressions prefixed with a `!`.

So when you have an entry `!fancymenu`, files and directories like

- `fancymenu_forge_2.11.1_MC_1.16.2-1.16.5.jar`
- `config/fancymenu`

will be excluded, because they contain the word `fancymenu`.

> **CAUTION!**
> Be very careful with lenient exclusions, as they can end up excluding more than you want.
<br>
> A simple, too leniently set, exclusion can render your server pack useless, beacuse it ended up
> excluding more than it should.
**PROCEED. WITH. CAUTION.**
> {.is-warning}

### Excluding in a directory you want to include

Usually you want the `config`-directory to be copied to the server pack, because it contains the
configuration for all the mods in your pack. However, this directory can contain files and folder
you may not want to *be* in the server pack, because it contains music-files, or pictures or
something else.

You can exclude files and folders from, say, the `config`-folder when generating a server pack
easily.

In the `Enter the list of directories or files in your modpack to include in the server pack:`
prefix any file or folder you want to exclude with `!`.

Examples:

- `!config/fancymenu`
- `!config/CustomMainMenu`
- `!config/clienttweaks-client.toml`

This will result in the whole `fancymenu`-directory *inside* the `config`-directory being excluded,
as well as the `CustomMainMenu`-directory and the `clienttweaks-client.toml`-file.

### Excluding all files of a specific type

You can exclude all files of a specific type by simply adding an entry along the lines
of `!.file_ending`. This will result in ServerPackCreator not copying **any** file which has this
file-ending.

Example(s):

- `!.ogg`
- `!.mp3`
- `!.png`
- `!.jpg`

## Include a world in your server pack

If you've got a world which you've prepared for your friends and you to play on, and you want to
make a server pack which contains said world so you can just boot up the server and get playing.
Well, good news, you can include that world in your server pack!

In `copyDirs`|**"Enter the list of directories or files on your modpack to include in the server
pack"** add an entry which references the world you want to include, like so:

- `saves/World-1` or
- `saves/my_awesome_world`

This will result in the specified world being copied to `World-1` or `my_awesome_world` in the base
directory of the server pack.

All you need to do now is to ensure that your `server.properties` is correctly set to use that world
like so:

`level-name=World-1` or
`level-name=my_awesome_world`

If you've already prepared a `server.properties`-file for your server, then make sure to
set `serverProperties`|**"Path to custom properties"** to the location of said properties-file so it
is automatically included in your server pack.

## Adding modpack-external files and folders to your server pack

You can add files/folder to your server pack which reside *outside* of the modpack from which you
want to generate a server pack. In order to include such a file or folder, you must explicitly
declare a `source:destination`-combination.

### File(s):

- `C:/Minecraft/ServerPackCreator/serverpackcreator.conf;my-spc.conf`
  This will copy the serverpackcreator.conf-file to the base-dir of the server pack as `my-spc.conf`
- `options.txt;misc/mc-options.txt`
  This will copy the options.txt-file in the base-dir of the modpack to the misc-dir of the server
  pack as `mc-options.txt`.
- '/home/griefed/Downloads/awesome.txt'
  This will copy the `awesome.txt`-file to the base-dir of the server pack as `awesome.txt`

**If any parent directory of the destination does not exist, they are created.**

### Folder(s) Examples:

- `C:/Minecraft/Game/Instances/Survive Create Prosper 4 custom (1)/packmenu;packmenu/files`
  This will copy the `packmenu`-directory and all its contents to the `packmenu/files`-directory in
  the server pack.
- `screenshots;my-beautiful-screenshots`
  This will copy the `screenshots`-directory and all its contents to the `my-beautiful-screenshots`
  -directory in the server pack.
- `/home/griefed/Downloads/some-worlds`
  This will copy the `some-worlds`-directory to the base-dir of the server pack as `some-worlds`.

## Lazy-mode

If you do not care, at all, to use the safety and convenience features ServerPackCreator provides,
and you simply want to create a server pack as fast as possible, simply set `copyDirs`|**Enter the
list of directories or files in your modpack to include in your server pack:** to `lazy_mode` and
ONLY `lazy_mode`.

This will result in the whole modpack being copied to the server pack. No exceptions. Mods will not
be scanned, no files will be excluded. EVERYTHING will be copied!

## Excluding files from ZIP-archives

You can globally configure files which should be excluded from ZIP-archives.
To do so, you need to edit your `serverpackcreator.properties`-file and change
the `de.griefed.serverpackcreator.serverpack.zip.exclude`-property to your liking.

There are some filters you can make use of if you want to, for example, exclude a files which has
the Minecraft version, modloader name, or modloader version in its name.

These filters are to be used in plain text, meaning:

1. `MINECRAFT_VERSION`
2. `MODLOADER`
3. `MODLOADER_VERSION`

If any file you specify contains any of these three strings, they will be replaced respectively.
Example:
`minecraft_server.MINECRAFT_VERSION.jar` becomes `minecraft_server.1.16.5.jar` when generating a
server pack for a modpack which uses Minecraft version 1.16.5.

The default setting
is `minecraft_server.MINECRAFT_VERSION.jar,server.jar,libraries/net/minecraft/server/MINECRAFT_VERSION/server-MINECRAFT_VERSION.jar`
and will exclude the Minecraft server jar, a file called `server.jar`, as well as the Minecraft
server jar should it be present in the `libraries`-folder.

To deactivate the exclusion alltogether,
set `de.griefed.serverpackcreator.serverpack.zip.exclude.enabled` to `false`

## Minecraft snapshots and pre-releases (3.12.x and up)

If your modloader supports Minecraft snapshots or pre-releases, you can use those, too.

Simply set `de.griefed.serverpackcreator.minecraft.snapshots` in your `serverpackcreator.properties`
to `true`

Then, when using the GUI, you will be able to select any weekly release, snapshot, pre-release and
more, just like that.

# Modes

ServerPackCreator has three main modes in which it can operate or in which you can use it.

- CLI (Commandline Interface)
- GUI (Graphical User Interface)
- Webservice (Provide a webservice through which to generate server packs)

There are a couple more ways to use/run ServerPackCreator which may or may not be helpful for you,
depending on how you plan on using it:

| Argument  | Description                                                                                                                                                                                                                 |
|-----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `-help`   | Print ServerPackCreators help to commandline.                                                                                                                                                                               |
| `-update` | Check whether a newer version of ServerPackCreator is available.                                                                                                                                                            |
| `-cgen`   | Run ServerPackCreators configuration generation.                                                                                                                                                                            |
| `-cli`    | Run ServerPackCreator in commandline-mode. If **no** graphical environment is supported, this is the default ServerPackCreator will enter,<br/>even when starting ServerPackCreator with no extra arguments at all.         |
| `-web`    | Run ServerPackCreator as a webservice.                                                                                                                                                                                      |
| `-gui`    | Run ServerPackCreator with our GUI. If a graphical environment is supported, this is the default ServerPackCreator will enter, even when<br/>starting ServerPackCreator with no extra arguments at all.                     |
| `--setup` | Set up and prepare the environment for subsequent runs of ServerPackCreator. This will create/copy all files needed for ServerPackCreator<br/>to function properly from inside its JAR-file and setup everything else, too. |

Each of these modes has its advantages and disadvantages.

## CLI:

| Advantages                                                                                                                                      | Disadvantages                                                                                                         |
|:------------------------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------|
| No need for a graphical environment. Can be used on a server to generate a server pack for immediate use.                                       | Gathering of information for a configuration file is tedious.                                                         |
| Step-by-Step generation of a configuration-file with the use of the `-cgen` argument.<br/>Generated config will be used immediately afterwards. | No convenience features file folder-browsing or jumping to the generated server pack after generation.                |
|                                                                                                                                                 | Debugging in case of a broken/erroring configuration file can be time consuming. Careful reading of logs is required. |
|                                                                                                                                                 | Manual editing of the configuration-file in case you want to change it.                                               |

## GUI:

| Advantages                                                                                                             | Disadvantages                     |
|:-----------------------------------------------------------------------------------------------------------------------|:----------------------------------|
| Browse the filesystem for folders and file required by the configuration.                                              | Requires a graphical environment. |
| Setting up a configuration by browsing the filesystem and selecting your Minecraft and modloader versions from a list. |                                   |
| Browsing the generated server pack after generation has finished.                                                      |                                   |
| Loading and saving different configurations for quick generation of multiple server packs in short succession.         |                                   |
| Edit the configuration in the GUI. No manual file-editing required.                                                    |                                   |

## Webservice:

| Advantages                                                                                                                                              | Disadvantages                                             |
|:--------------------------------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------|
| No installation/setup required if a public instance is available somewhere.                                                                             | Requires someone to setup a publicly accessible instance. |
| Voting system to let users indicate whether a generated server pack works.                                                                              | Requires somewhat modern browser versions.                |
| Open REST API. Interaction with the webservice does not *require* a browser.<br/>You can request a server pack generation and/or download from the CLI. | Not all browers may be supported.                         |

# Addons

ServerPackCreator gives you the ability to use addons, which can enhance your experience in a
limited amount of ways.

Any given addon has the chance to do things

- Before a server pack is generated
- After a server pack was generated, but BEFORE the ZIP-archive is created
- After a server pack and its ZIP-archive were generated
- Adding extra tabs to the GUI, in which more additional features can then be added

For a list of addons, check out
the [ServerPackCreator Addons Overview](https://griefed.github.io/ServerPackCreator-Addons-Overview/#/)

The first three entries are examples I made to demonstrate some basic capabilities of such addons.

## Installing an addon

If you have an addon you would like to enhance your ServerPackCreator-experience with, simply
download it and put it into the `plugins`-directory which resides in the base-directory of
ServerPackCreator.

```
ServerPackCreator
    ├── lang
    ├── logs
    │   └── archive
    ├── plugins <--------- 
    ├── server-packs
    │   ├── Survive Create Prosper 4 custom (1)
    │   └── World of Dragons II
    ├── server_files
    └── work
        ├── modpacks
        └── temp
```

Example with `ExampleAddon-3.0.5.jar` from
the [Example Addon Repository](https://github.com/Griefed/ServerPackCreatorExampleAddon/releases/tag/3.0.5)
on GitHub.

```
ServerPackCreator
├── lang
├── logs
├── plugins
│   ├── ExampleAddon-3.0.5.jar <------
│   └── disabled.txt
├── server-packs
├── server_files
└── work
    └── ...
```

**Important:** If ServerPackCreator was running during the installation of an addon, you need to
restart it in order for the addon to become available.

## Disabling an addon

In order to deactivate any installed addon, edit the `disabled.txt`-file in the `plugins`-directory.
If these don't exist, create them.

```
ServerPackCreator
├── lang
├── logs
├── plugins
│   ├── ExampleAddon-3.0.5.jar
│   └── disabled.txt <------
├── server-packs
├── server_files
└── work
    └── ...
```

Then, find the plugin ID of the installed addon. This can be mentioned in the addons repository, but
can also be found in the addons manifest.

Example for my example addon: `ExampleAddon-3.0.5.jar\META-INF\MANIFEST.MF`.
Look for the entry `Plugin-Id:`, in this case: `Plugin-Id: example-plugin`

Then, in your `disabled.txt`, add a new line containing `example-plugin`, or rather, whatever ID of
any addon you want to deactivate:

```
########################################
# - Load all plugins except these.   - #
# - Add one plugin-id per line.      - #
########################################
example-plugin
```

**Important:** If ServerPackCreator was running during the disabling of an addon, you need to
restart it in order for the addon to become disabled.

# Configuration

## serverpackcreator.conf

The serverpackcreator.conf file allows you to customize a couple of different things:

| Variable                  | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
|---------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| modpackDir                | The path to the directory/ZIP-archive where your modpack resides in.                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| clientMods                | List of client-side only mods which are to be deleted from the serverpack. You only need to specify the beginning of the filename up, but excluding,<br/>the version number. ServerPackCreator checks whether any of the mods which are copied from the modpack to the serverpack start with any strings in this list and,<br/>if there's a match, deletes that file from the serverpack. Check out the [example](https://gist.github.com/Griefed/090cc7c1c2b283daa4b46f6cb85e5e00) for an idea of how it's supposed to look. |
| copyDirs                  | List for directories which are to be copied to the serverpack. If you specify a world from the `saves`-directory, ServerPackCreator will copy the the<br/>specified world to the base directory of the serverpack. In other words, `/saves/MyAwesomeWorld` becomes `/MyAwesomeWorld`.                                                                                                                                                                                                                                     |
| includeServerInstallation | Whether to install a Forge/Fabric server for the serverpack. Must be `true` or `false`.                                                                                                                                                                                                                                                                                                                                                                                                                               |
| javaPath                  | Path to the Java Installation. On Linux systems use `which java` to find the location of your Java install. On Windows use `where java` and exclude the `.exe`-part.                                                                                                                                                                                                                                                                                                                                                  |
| minecraftVersion          | The version of Minecraft for which to install the modloader server. The same version of Minecraft your modpack uses.                                                                                                                                                                                                                                                                                                                                                                                                  |
| modLoader                 | Which modloader to install. Must be either "Forge" or "Fabric". The same modloader your modpack uses.                                                                                                                                                                                                                                                                                                                                                                                                                 |
| modLoaderVersion          | Specific Modloader version to install the server in the serverpack. The same version your modpack uses.                                                                                                                                                                                                                                                                                                                                                                                                               |
| includeServerIcon         | Whether to include server-icon.png in your serverpack. Must be `true` or `false`.                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| includeServerProperties   | Whether to include server.properties in your serverpack. Must be `true` or `false`.                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| includeZipCreation        | Whether to create a zip-file of your serverpack, saved in the directory you specified with `modpackDir`. Must be `true` or `false`.                                                                                                                                                                                                                                                                                                                                                                                   |
| javaArgs                  | JVM flags / Java Args to add to the generated start-scripts. Set to "empty" to not use any in your start-scripts.                                                                                                                                                                                                                                                                                                                                                                                                     |
| serverPackSuffix          | A suffix to append to the name of the server pack directory and server pack ZIP-archive. Illegal characters are<br/>/ < > : " \ &#124; ? * # % & { } $ ! ' @ + ´ \` = and must not end with a SPACE<code>&#32;&#32;</code> or a DOT<code>&#32;.&#32;&#32;</code>                                                                                                                                                                                                                                                          | |
| serverIconPath            | Path to a custom server-icon.png-file to include in the server pack.                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| serverPropertiesPath      | Path to a custom server.properties-file to include in the server pack.                                                                                                                                                                                                                                                                                                                                                                                                                                                |

## serverpackcreator.properties

```properties
de.griefed.serverpackcreator.versioncheck.prerelease=false
de.griefed.serverpackcreator.language=en_us
de.griefed.serverpackcreator.configuration.fallbackmodslist=Armor Status HUD-,[1.12.2]bspkrscore-,\
  [1.12.2]DamageIndicatorsMod-,3dskinlayers-,Absolutely-Not-A-Zoom-Mod-,AdvancedChat-,AdvancedCompas-,\
  AdvancementPlaques-,Ambience,AmbientEnvironment-,AmbientSounds_,antighost-,anviltooltipmod-,appleskin-,\
  armorchroma-,armorpointspp-,ArmorSoundTweak-,AromaBackup-,authme-,autobackup-,autoreconnect-,\
  auto-reconnect-,axolotl-item-fix-,backtools-,Backups-,bannerunlimited-,Batty's Coordinates PLUS Mod,\
  beenfo-1.19-,BetterAdvancements-,BetterAnimationsCollection-,betterbiomeblend-,BetterDarkMode-,\
  BetterF3-,BetterFoliage-,BetterPingDisplay-,BetterPlacement-,better-recipe-book-,BetterTaskbar-,\
  BetterThirdPerson,BetterTitleScreen-,bhmenu-,BH-Menu-,blur-,Blur-,borderless-mining-,BorderlessWindow-,\
  catalogue-,charmonium-,chat_heads-,cherishedworlds-,ChunkAnimator-,cirback-1.0-,classicbar-,clickadv-,\
  clienttweaks-,ClientTweaks_,combat_music-,configured-,controllable-,Controller Support-,Controlling-,\
  CraftPresence-,CTM-,cullleaves-,cullparticles-,custom-crosshair-mod-,CustomCursorMod-,customdiscordrpc-,\
  CustomMainMenu-,darkness-,dashloader-,defaultoptions-,DefaultOptions_,DefaultSettings-,DeleteWorldsToTrash-,\
  desiredservers-,DetailArmorBar-,Ding-,discordrpc-,DistantHorizons-,drippyloadingscreen-,drippyloadingscreen_,\
  DripSounds-,Durability101-,DurabilityNotifier-,dynamic-fps-,dynamiclights-,dynamic-music-,\
  DynamicSurroundings-,DynamicSurroundingsHuds-,dynmus-,effective-,EffectsLeft-,eggtab-,eguilib-,\
  eiramoticons-,EiraMoticons_,EnchantmentDescriptions-,enchantment-lore-,EnhancedVisuals_,entityculling-,\
  entity-texture-features-,EquipmentCompare-,exhaustedstamina-,extremesoundmuffler-,FabricCustomCursorMod-,\
  fabricemotes-,Fallingleaves-,fancymenu_,fancymenu_video_extension,FancySpawnEggs,FancyVideo-API-,findme-,\
  FirstPersonMod,flickerfix-,fm_audio_extension_,FogTweaker-,ForgeCustomCursorMod-,forgemod_VoxelMap-,\
  FPS-Monitor-,FpsReducer-,FpsReducer2-,freelook-,ftb-backups-,ftbbackups2-,FullscreenWindowed-,galacticraft-rpc-,\
  GameMenuModOption-,gamestagesviewer-,grid-,HealthOverlay-,hiddenrecipebook_,HorseStatsMod-,infinitemusic-,\
  InventoryEssentials_,InventoryHud_[1.17.1].forge-,inventoryprofiles,InventorySpam-,InventoryTweaks-,invtweaks-,\
  ItemBorders-,ItemPhysicLite_,ItemStitchingFix-,itemzoom,itlt-,JBRA-Client-,jeed-,jehc-,jeiintegration_,\
  justenoughbeacons-,JustEnoughCalculation-,justenoughdrags-,JustEnoughEffects-,just-enough-harvestcraft-,\
  JustEnoughProfessions-,JustEnoughResources-,justzoom_,keymap-,keywizard-,konkrete_,konkrete_forge_,lazydfu-,\
  LegendaryTooltips,LegendaryTooltips-,lightfallclient-,LightOverlay-,light-overlay-,LLOverlayReloaded-,\
  loadmyresources_,lock_minecart_view-,lootbeams-,LOTRDRP-,lwl-,magnesium_extras-,maptooltip-,massunbind,\
  mcbindtype-,mcwifipnp-,medievalmusic-,mightyarchitect-,mindful-eating-,minetogether-,MoBends,mobplusplus-,\
  modcredits-,modernworldcreation_,modmenu-,modnametooltip-,modnametooltip_,moreoverlays-,MouseTweaks-,\
  mousewheelie-,movement-vision-,multihotbar-,musicdr-,music-duration-reducer-,MyServerIsCompatible-,Neat-,\
  Neat ,neiRecipeHandlers-,NekosEnchantedBooks-,ngrok-lan-expose-mod-,NoAutoJump-,NoFog-,nopotionshift_,\
  notenoughanimations-,Notes-,NotifMod-,oculus-,OldJavaWarning-,openbackup-,OptiFine,OptiForge,OptiForge-,\
  ornaments-,overloadedarmorbar-,PackMenu-,PackModeMenu-,panorama-,paperdoll-,phosphor-,PickUpNotifier-,\
  Ping-,preciseblockplacing-,PresenceFootsteps-,realm-of-lost-souls-,ReAuth-,rebrand-,replanter-,ResourceLoader-,\
  ResourcePackOrganizer,RPG-HUD-,rubidium-,rubidium_extras-,screenshot-to-clipboard-,ShoulderSurfing-,\
  ShulkerTooltip-,shutupexperimentalsettings-,shutupmodelloader-,signtools-,simpleautorun-,simplebackup-,\
  SimpleBackups-,SimpleDiscordRichPresence-,simple-rpc-,SimpleWorldTimer-,smartcursor-,smoothboot-,\
  smoothfocus-,sounddeviceoptions-,SoundFilters-,soundreloader-,SpawnerFix-,spoticraft-,tconplanner-,\
  textile_backup-,timestamps-,Tips-,TipTheScales-,Toast Control-,ToastControl-,Toast-Control-,tooltipscroller-,\
  torchoptimizer-,torohealth-,totaldarkness,toughnessbar-,TRansliterationLib-,TravelersTitles-,VoidFog-,\
  WindowedFullscreen-,wisla-,WorldNameRandomizer-,xlifeheartcolors-,yisthereautojump-
de.griefed.serverpackcreator.configuration.fallbackmodslist.regex=^Armor Status HUD-.*$,\
  ^[1.12.2]bspkrscore-.*$,^[1.12.2]DamageIndicatorsMod-.*$,^3dskinlayers-.*$,^Absolutely-Not-A-Zoom-Mod-.*$,\
  ^AdvancedChat-.*$,^AdvancedCompas-.*$,^AdvancementPlaques-.*$,^Ambience.*$,^AmbientEnvironment-.*$,\
  ^AmbientSounds_.*$,^antighost-.*$,^anviltooltipmod-.*$,^appleskin-.*$,^armorchroma-.*$,\
  ^armorpointspp-.*$,^ArmorSoundTweak-.*$,^AromaBackup-.*$,^authme-.*$,^autobackup-.*$,^autoreconnect-.*$,\
  ^auto-reconnect-.*$,^axolotl-item-fix-.*$,^backtools-.*$,^Backups-.*$,^bannerunlimited-.*$,\
  ^Batty's Coordinates PLUS Mod.*$,^beenfo-1.19-.*$,^BetterAdvancements-.*$,^BetterAnimationsCollection-.*$,\
  ^betterbiomeblend-.*$,^BetterDarkMode-.*$,^BetterF3-.*$,^BetterFoliage-.*$,^BetterPingDisplay-.*$,\
  ^BetterPlacement-.*$,^better-recipe-book-.*$,^BetterTaskbar-.*$,^BetterThirdPerson.*$,^BetterTitleScreen-.*$,\
  ^bhmenu-.*$,^BH-Menu-.*$,^blur-.*$,^borderless-mining-.*$,^BorderlessWindow-.*$,^catalogue-.*$,^charmonium-.*$,\
  ^chat_heads-.*$,^cherishedworlds-.*$,^ChunkAnimator-.*$,^cirback-1.0-.*$,^classicbar-.*$,^clickadv-.*$,\
  ^clienttweaks-.*$,^ClientTweaks_.*$,^combat_music-.*$,^configured-.*$,^controllable-.*$,^Controller Support-.*$,\
  ^Controlling-.*$,^CraftPresence-.*$,^CTM-.*$,^cullleaves-.*$,^cullparticles-.*$,^custom-crosshair-mod-.*$,\
  ^CustomCursorMod-.*$,^customdiscordrpc-.*$,^CustomMainMenu-.*$,^darkness-.*$,^dashloader-.*$,^defaultoptions-.*$,\
  ^DefaultOptions_.*$,^DefaultSettings-.*$,^DeleteWorldsToTrash-.*$,^desiredservers-.*$,^DetailArmorBar-.*$,\
  ^Ding-.*$,^discordrpc-.*$,^DistantHorizons-.*$,^drippyloadingscreen-.*$,^drippyloadingscreen_.*$,^DripSounds-.*$,\
  ^Durability101-.*$,^DurabilityNotifier-.*$,^dynamic-fps-.*$,^dynamiclights-.*$,^dynamic-music-.*$,\
  ^DynamicSurroundings-.*$,^DynamicSurroundingsHuds-.*$,^dynmus-.*$,^effective-.*$,^EffectsLeft-.*$,^eggtab-.*$,\
  ^eguilib-.*$,^eiramoticons-.*$,^EiraMoticons_.*$,^EnchantmentDescriptions-.*$,^enchantment-lore-.*$,\
  ^EnhancedVisuals_.*$,^entityculling-.*$,^entity-texture-features-.*$,^EquipmentCompare-.*$,^exhaustedstamina-.*$,\
  ^extremesoundmuffler-.*$,^FabricCustomCursorMod-.*$,^fabricemotes-.*$,^Fallingleaves-.*$,^fancymenu_.*$,\
  ^fancymenu_video_extension.*$,^FancySpawnEggs.*$,^FancyVideo-API-.*$,^findme-.*$,^FirstPersonMod.*$,\
  ^flickerfix-.*$,^fm_audio_extension_.*$,^FogTweaker-.*$,^ForgeCustomCursorMod-.*$,^forgemod_VoxelMap-.*$,\
  ^FPS-Monitor-.*$,^FpsReducer-.*$,^FpsReducer2-.*$,^freelook-.*$,^ftb-backups-.*$,^ftbbackups2-.*$,^FullscreenWindowed-.*$,\
  ^galacticraft-rpc-.*$,^GameMenuModOption-.*$,^gamestagesviewer-.*$,^grid-.*$,^HealthOverlay-.*$,^hiddenrecipebook_.*$,\
  ^HorseStatsMod-.*$,^infinitemusic-.*$,^InventoryEssentials_.*$,^InventoryHud_[1.17.1].forge-.*$,^inventoryprofiles.*$,\
  ^InventorySpam-.*$,^InventoryTweaks-.*$,^invtweaks-.*$,^ItemBorders-.*$,^ItemPhysicLite_.*$,^ItemStitchingFix-.*$,\
  ^itemzoom.*$,^itlt-.*$,^JBRA-Client-.*$,^jeed-.*$,^jehc-.*$,^jeiintegration_.*$,^justenoughbeacons-.*$,\
  ^JustEnoughCalculation-.*$,^justenoughdrags-.*$,^JustEnoughEffects-.*$,^just-enough-harvestcraft-.*$,\
  ^JustEnoughProfessions-.*$,^JustEnoughResources-.*$,^justzoom_.*$,^keymap-.*$,^keywizard-.*$,^konkrete_.*$,\
  ^konkrete_forge_.*$,^lazydfu-.*$,^LegendaryTooltips.*$,^LegendaryTooltips-.*$,^lightfallclient-.*$,^LightOverlay-.*$,\
  ^light-overlay-.*$,^LLOverlayReloaded-.*$,^loadmyresources_.*$,^lock_minecart_view-.*$,^lootbeams-.*$,\
  LOTRDRP-.*$,^lwl-.*$,^magnesium_extras-.*$,^maptooltip-.*$,^massunbind.*$,^mcbindtype-.*$,^mcwifipnp-.*$,\
  ^medievalmusic-.*$,^mightyarchitect-.*$,^mindful-eating-.*$,^minetogether-.*$,^MoBends.*$,^mobplusplus-.*$,\
  ^modcredits-.*$,^modernworldcreation_.*$,^modmenu-.*$,^modnametooltip-.*$,^modnametooltip_.*$,^moreoverlays-.*$,\
  ^MouseTweaks-.*$,^mousewheelie-.*$,^movement-vision-.*$,^multihotbar-.*$,^musicdr-.*$,^music-duration-reducer-.*$,\
  ^MyServerIsCompatible-.*$,^Neat-.*$,^Neat .*$,^neiRecipeHandlers-.*$,^NekosEnchantedBooks-.*$,\
  ^ngrok-lan-expose-mod-.*$,^NoAutoJump-.*$,^NoFog-.*$,^nopotionshift_.*$,^notenoughanimations-.*$,^Notes-.*$,\
  ^NotifMod-.*$,^oculus-.*$,^OldJavaWarning-.*$,^openbackup-.*$,^OptiFine.*$,^OptiForge.*$,^OptiForge-.*$,\
  ^ornaments-.*$,^overloadedarmorbar-.*$,^PackMenu-.*$,^PackModeMenu-.*$,^panorama-.*$,^paperdoll-.*$,\
  ^phosphor-.*$,^PickUpNotifier-.*$,^Ping-.*$,^preciseblockplacing-.*$,^PresenceFootsteps-.*$,^realm-of-lost-souls-.*$,\
  ^ReAuth-.*$,^rebrand-.*$,^replanter-.*$,^ResourceLoader-.*$,^ResourcePackOrganizer.*$,^RPG-HUD-.*$,^rubidium-.*$,\
  ^rubidium_extras-.*$,^screenshot-to-clipboard-.*$,^ShoulderSurfing-.*$,^ShulkerTooltip-.*$,^shutupexperimentalsettings-.*$,\
  ^shutupmodelloader-.*$,^signtools-.*$,^simpleautorun-.*$,^simplebackup-.*$,^SimpleBackups-.*$,\
  ^SimpleDiscordRichPresence-.*$,^simple-rpc-.*$,^SimpleWorldTimer-.*$,^smartcursor-.*$,^smoothboot-.*$,^smoothfocus-.*$,\
  ^sounddeviceoptions-.*$,^SoundFilters-.*$,^soundreloader-.*$,^SpawnerFix-.*$,^spoticraft-.*$,^tconplanner-.*$,\
  ^textile_backup-.*$,^timestamps-.*$,^Tips-.*$,^TipTheScales-.*$,^Toast Control-.*$,^ToastControl-.*$,^Toast-Control-.*$,\
  ^tooltipscroller-.*$,^torchoptimizer-.*$,^torohealth-.*$,^totaldarkness.*$,^toughnessbar-.*$,^TRansliterationLib-.*$,\
  ^TravelersTitles-.*$,^VoidFog-.*$,^WindowedFullscreen-.*$,^wisla-.*$,^WorldNameRandomizer-.*$,^xlifeheartcolors-.*$,\
  ^yisthereautojump-.*$
de.griefed.serverpackcreator.configuration.hastebinserver=https://haste.zneix.eu/documents
de.griefed.serverpackcreator.serverpack.autodiscovery.enabled=true
de.griefed.serverpackcreator.gui.darkmode=true
de.griefed.serverpackcreator.configuration.directories.serverpacks=server-packs
de.griefed.serverpackcreator.serverpack.cleanup.enabled=true
de.griefed.serverpackcreator.serverpack.overwrite.enabled=true
de.griefed.serverpackcreator.configuration.directories.shouldexclude=overrides,packmenu,resourcepacks,server_pack,fancymenu
de.griefed.serverpackcreator.spring.schedules.database.cleanup=0 0 0 * * *
de.griefed.serverpackcreator.spring.schedules.files.cleanup=0 0 0 * * *
de.griefed.serverpackcreator.spring.schedules.versions.refresh=0 0 0 * * *
de.griefed.serverpackcreator.spring.artemis.queue.max_disk_usage=90
de.griefed.serverpackcreator.configuration.saveloadedconfig=false
de.griefed.serverpackcreator.configuration.directories.mustinclude=mods,config,defaultconfigs,scripts,saves,seeds,libraries
de.griefed.serverpackcreator.serverpack.autodiscovery.filter=START
```

| Property                                                             | Description                                                                                                                                                                  |
|----------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| de.griefed.serverpackcreator.versioncheck.prerelease                 | `true` or `false`. Whether to check for available pre-releases, too, when checking for updates.                                                                              |
| de.griefed.serverpackcreator.language                                | The language in which ServerPackCreator should run.                                                                                                                          |
| de.griefed.serverpackcreator.configuration.fallbackmodslist          | Comma separated fallback-list of clientside-only mods. Do not edit this manually.                                                                                            |
| de.griefed.serverpackcreator.configuration.fallbackmodslist.regex    | Comma separated fallback-list of clientside-only mods in regex format. Do not edit this manually.                                                                            |
| de.griefed.serverpackcreator.configuration.hastebinserver            | Address of a HasteBin server to use for config and logs uploading.                                                                                                           |
| de.griefed.serverpackcreator.serverpack.autodiscovery.enabled        | `true` or `false`. Whether to try and determine sideness of mods in a modpack automatically and exclude them if they are clientside-only. Set this to `false` to disable it. |
| de.griefed.serverpackcreator.gui.darkmode                            | GUI-only. `true` or `false`. Whether to use dark-mode in the GUI.                                                                                                            |
| de.griefed.serverpackcreator.configuration.directories.serverpacks   | The directory in which server packs will be generated and stored in.                                                                                                         |
| de.griefed.serverpackcreator.serverpack.cleanup.enabled              | `true` or `false`. Whether to cleanup files after generating a server pack.                                                                                                  |
| de.griefed.serverpackcreator.serverpack.overwrite.enabled            | `true` or `false`. Whether an already existing server pack should be overwritten.                                                                                            |
| de.griefed.serverpackcreator.configuration.directories.shouldexclude | List of directories which should not be in a server pack.                                                                                                                    |
| de.griefed.serverpackcreator.spring.schedules.database.cleanup       | Web-only. Cron-schedule at which checks are run and server packs cleaned up.                                                                                                 |
| de.griefed.serverpackcreator.spring.schedules.files.cleanup          | Web-only. Cron-schedule at which checks are run and files from server pack generations are cleaned up.                                                                       |
| de.griefed.serverpackcreator.spring.schedules.versions.refresh       | Web-only. Cron-schedule at which the available Minecraft, Forge and Fabric versions are refreshed.                                                                           |
| de.griefed.serverpackcreator.spring.artemis.queue.max_disk_usage     | Web-only. Maximum disk usage in percent at which no new tasks are accepted, preventing the generation of new server packs.                                                   |
| de.griefed.serverpackcreator.configuration.saveloadedconfig          | GUI-only. `true` or `false`. Whether to overwrite the last manually loaded configuration file, too.                                                                          |
| de.griefed.serverpackcreator.configuration.directories.mustinclude   | List of directories which must be included in a server pack.                                                                                                                 |
| de.griefed.serverpackcreator.serverpack.autodiscovery.filter         | Filter method by which to exclude user-specified clientside-only mods. `START`, `END`, `REGEX`, `CONTAIN`, `EITHER`                                                          |

`application.properties`

```properties
server.port=8080
server.error.whitelabel.enabled=false
server.tomcat.basedir=.
server.tomcat.accesslog.enabled=true
server.tomcat.accesslog.directory=logs
server.tomcat.accesslog.file-date-format=_yyyy-MM-dd
server.tomcat.accesslog.prefix=tomcat_access
server.tomcat.accesslog.suffix=.log
server.tomcat.accesslog.pattern=common
spring.output.ansi.enabled=ALWAYS
server.error.include-message=ALWAYS
server.error.include-stacktrace=ALWAYS
spring.application.name=ServerPackCreator
spring.datasource.url=jdbc:sqlite:serverpackcreator.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.sqlite.hibernate.dialect.SQLiteDialect
spring.jpa.open-in-view=false
spring.jdbc.template.query-timeout=3600
spring.transaction.default-timeout=3600
spring.artemis.mode=embedded
spring.artemis.embedded.persistent=true
spring.artemis.embedded.data-directory=work/artemis
spring.datasource.tomcat.max-active=50
spring.datasource.tomcat.max-idle=15
spring.datasource.tomcat.min-idle=8
spring.servlet.multipart.max-file-size=500MB
spring.servlet.multipart.max-request-size=500MB
spring.config.import=optional:file:./serverpackcreator.properties
```

If a property above has no description in the table below, it is not to be touched. Changing any of
these can break ServerPackCreator or cause otherwise unwanted behaviour.
Changes to properties not described below are not supported. If you changed a property you were not
meant to fiddle around with, do not report an issue. Such issues will be closed without further
comment.

| Property                                  | Description                                                                                            |
|-------------------------------------------|--------------------------------------------------------------------------------------------------------|
| server.port                               | The port at which ServerPackCreator as a webservice will be available at.                              |
| spring.datasource.url                     | Path to and name of the SQLite database which the webservice will use.                                 |
| spring.artemis.embedded.data-directory    | The path to and name of the directory in which Artemis will store queues and task related information. |
| spring.servlet.multipart.max-file-size    | Maximum filesize for uploads.                                                                          |
| spring.servlet.multipart.max-request-size | Maximum request size for file uploads.                                                                 |
| spring.config.import                      | Additional properties-file to import properties from.                                                  |

# Localization

If you wish to run ServerPackCreator with your locale (if it is already supported), you can either:

1. Run `java -jar ServerPackCreator-X.X.X.jar -lang your_locale` for
   example `java -jar ServerPackCreator-X.X.X.jar -lang en_us`. This will create the
   lang.properties-file with your specified locale.
2. Running `java -jar ServerPackCreator-x.x.x.jar` without `-lang en_us` or any other language will
   set the locale to en_us by default and create the lang.properties-file.
3. Modify the `serverpackcreator.properties`-file in the same directory as
   ServerPackCreator-X-X-X.jar and set your locale like this `lang=your_locale` for
   example `lang=en_us`

If you want to customize the translation of ServerPackCreator, you can edit the files in the `lang`
-directory. As of me writing this it should, by default, contain `lang_de_de.properties`
, `lang_en_us.properties` and `lang_uk_ua.properties`.

The most up-to-date translation will always be the english one, as I make ServerPackCreator with
english in mind.

## Adding a translation

Say you want to translate ServerPackCreator to german. You would need both
the `lang_de_de.properties`-file and the `lang_en_us.properties`-file.

In the english properties, you will see entries like

```properties
menubar.gui.menu.file=File
menubar.gui.menu.edit=Edit
menubar.gui.menu.view=View
menubar.gui.menu.about=About
menubar.gui.menu.help=Help
```

So, in order to translate them to german, in your `lang_de_de.properties`-file, you would add

```properties
menubar.gui.menu.file=Datei
menubar.gui.menu.edit=Bearbeiten
menubar.gui.menu.view=Ansicht
menubar.gui.menu.about=Info
menubar.gui.menu.help=Hilfe
```

Then, either in your `serverpackcreator.properties`
set `de.griefed.serverpackcreator.language=de_de` or launch ServerPackCreator with the
argument `-lang=de_de`.

Voila! The menubar will now have german translations!

Keep in mind when using languages other than `en_us`: Any key not found in your currently set
language will fall back to the english default.