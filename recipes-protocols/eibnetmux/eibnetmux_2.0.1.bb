SUMMARY = "EIBnetmux extends the IP-reach of the KNX bus and supports multiple concurrent clients"
SECTION = "base"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${S}/README;md5=18891ec537aadddd4df6c477cb8d813c"

SRC_URI = "https://github.com/condo4/eibnetmux/archive/${PV}.tar.gz"

inherit autotools gettext pkgconfig

DEPENDS += "pth polarssl zlogger"

SRC_URI[md5sum] = "635d6fc099cc1ede25f5c792e6111ea9"
SRC_URI[sha256sum] = "156bc55485aa1773c39f9e1494e0e72907905a2e79662d387e2d88569e570c7b"

