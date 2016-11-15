SUMMARY = "Linknx is an automation platform providing high level functionalities to EIB/KNX installation. The rules engine allows execution of actions based on complex logical conditions and timers. Lightweight design allows it to run on embedded Linux"
SECTION = "base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=2c1c00f9d3ed9e24fa69b932b7e7aff2"

SRC_URI = "https://github.com/condo4/linknx/archive/master.tar.gz \
			file://use-pkgconfig-instead-of-pth-config.patch"

inherit autotools-brokensep gettext pkgconfig

S = "${WORKDIR}/linknx-master"

EXTRA_OECONF = "--without-pth-test "

DEPENDS += "pthsem mariadb"
RDEPENDS_${PN} = "pthsem mariadb knxd libcurl"

SRC_URI[md5sum] = "2ed33a43b0124b1f02ca0110f0ffb520"
SRC_URI[sha256sum] = "9e4ad914756c103b4802bede5f65c7f98e5fb86b8c29d62eac8eb169601a7450"
