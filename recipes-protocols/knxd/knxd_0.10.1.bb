SUMMARY = "KNXD extends the IP-reach of the KNX bus and supports multiple concurrent clients"
SECTION = "base"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=8264535c0c4e9c6c335635c4026a8022"

SRC_URI = "https://github.com/knxd/knxd/archive/v${PV}.tar.gz \
			file://use-pkgconfig-instead-of-pth-config.patch"

inherit autotools-brokensep gettext pkgconfig

EXTRA_OECONF = "--without-pth-test --enable-eibnetip --enable-eibnetiptunnel --enable-usb --enable-eibnetipserver"

DEPENDS += "pthsem libusb1"
RDEPENDS_${PN} = "pthsem"

SRC_URI[md5sum] = "954350cf0d51fdfb31300945f111e6f3"
SRC_URI[sha256sum] = "9cf9019dcd8456b49f5b51ae78042f4b530845029d0d569230016522e8063cc6"

do_install_append() {
	install -d ${D}/${sysconfdir}/init.d
	install -m 0655 ${S}/debian/knxd.init ${D}${sysconfdir}/init.d/knxd.init
}

PACKAGES =+ " ${PN}-exemples-dbg  ${PN}-exemples "

FILES_${PN}-exemples += "/usr/share/knxd/examples"
FILES_${PN}-exemples-dbg += "/usr/share/knxd/examples/bin/.debug"
