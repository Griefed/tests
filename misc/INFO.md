
---

### Installer-Notes

It is recommended to have administration-privileges when using the Windows and macOS installers, as ServerPackCreator may, or may not, depending on how you want to use it, require heightened file-access privileges depending on which files or folders you want to include in a given server pack.

### macOS PKG

Because I am not a member of the [Apple Developer Program](https://developer.apple.com/programs/), and I can not afford to become one, the PKG-file and contained software are unsigned.
Because of that, macOS will complain about potential security risks and refuse to install and run ServerPackCreator. To circumvent this issue, if you so desire, please see the following official guides depending on your version of macOS: [Ventura](https://support.apple.com/guide/mac-help/open-a-mac-app-from-an-unidentified-developer-mh40616/mac), [Monterey](https://support.apple.com/guide/mac-help/open-a-mac-app-from-an-unidentified-developer-mh40616/12.0/mac/12.0).
You may choose to install it anyway, please be aware though, that doing so is, in general, not a safe practice. For ServerPackCreator, though, it is unfortunately the only way to run the installer.

### Windows MSI

As with the macOS PKG, the Windows MSI is also unsigned, thus triggering a security warning by Windows. For the same reason as for the macOS PKG, I can not afford the required certificate to sign and verify the MSI.
To run the installer anyway, hit "More information" when the Windows SmartScreen-message pops up. Afterwards, a button will appear which will let you run the installer anyway.
You may choose to install it anyway, please be aware though, that doing so is, in general, not a safe practice. For ServerPackCreator, though, it is unfortunately the only way to run the installer.

### Ubuntu DEB

The DEB-installer is purely for Ubuntu and Ubuntu-derivative Linux distributions. 