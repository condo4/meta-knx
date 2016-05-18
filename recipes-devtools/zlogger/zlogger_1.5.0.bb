SUMMARY = "EIBnetmux extends the IP-reach of the KNX bus and supports multiple concurrent clients"
SECTION = "base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/README;md5=80921416aadbf17a36d4e8d023a58f1f"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/zlogger/zlogger/${PV}/zlogger-${PV}.tar.bz2"

inherit autotools-brokensep

DEPENDS += "pth"
RDEPENDS_${PN} += "pth"

SRC_URI[md5sum] = "c1d537d30b7a527c1d42bd70c55dc0b7"
SRC_URI[sha256sum] = "87039573552a0af5e2d7fddfc91518526c527f37ff4eebc858a9964e9cdf4458"

EXTRA_OECONF += "--with-plugins"

FILES_${PN}-dbg += "/usr/lib/zlogger/plugins/1.5.0/.debug"
FILES_${PN}-staticdev += "/usr/lib/zlogger/plugins/1.5.0/*.a"