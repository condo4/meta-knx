SUMMARY = "Linknx is an automation platform providing high level functionalities to EIB/KNX installation. The rules engine allows execution of actions based on complex logical conditions and timers. Lightweight design allows it to run on embedded Linux"
SECTION = "base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=2c1c00f9d3ed9e24fa69b932b7e7aff2"

SRC_URI = "https://github.com/linknx/linknx/archive/0.0.1.33.tar.gz \
			file://use-pkgconfig-instead-of-pth-config.patch"

inherit autotools-brokensep gettext pkgconfig

S = "${WORKDIR}/linknx-${PV}"

EXTRA_OECONF = "--without-pth-test "

DEPENDS += "pthsem mariadb"
RDEPENDS_${PN} = "pthsem mariadb knxd libcurl"

SRC_URI[md5sum] = "dd7d5fbda9059693587dd7743dfe7a8d"
SRC_URI[sha256sum] = "635b8fbd3477fd7d85a95955b93f327cd5a389db80dc18cdae04de19a3a8a972"
