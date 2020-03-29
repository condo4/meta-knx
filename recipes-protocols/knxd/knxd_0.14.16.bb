SUMMARY = "KNXD extends the IP-reach of the KNX bus and supports multiple concurrent clients"
SECTION = "base"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=8264535c0c4e9c6c335635c4026a8022"

SRC_URI = "https://github.com/knxd/knxd/archive/v${PV}.tar.gz"

S = "${WORKDIR}/knxd-${PV}"

SRC_URI[md5sum] = "32e900e8caf75097ba0fdc5c7e3be42c"
SRC_URI[sha256sum] = "eb5fc0eab9ab8461670c4ef95811c438fe0c8242759919b64048b615b6533dd0"

DEPENDS += "cmake-native libusb1 libev ${@bb.utils.contains('DISTRO_FEATURES', 'systemd','systemd', '', d)}"
RDEPENDS_${PN} = "libusb1 ${@bb.utils.contains('DISTRO_FEATURES', 'systemd','libsystemd', '', d)}"


EXTRA_OECONF = " \
    --enable-eibnetip \
    --enable-eibnetiptunnel \
    --enable-eibnetipserver \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd','--enable-systemd', '', d)} \
"

inherit autotools-brokensep gettext pkgconfig useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system knxd"
USERADD_PARAM_${PN} =  "--system --home /var/lib/knxd --no-create-home --gid knxd knxd"

do_install_append() {
    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)};
    then
        install -d ${D}/${sysconfdir}
        install -m 644 ${S}/systemd/knxd.conf ${D}/${sysconfdir}
        install -d ${D}${systemd_unitdir}/system
        install -m 644 ${S}/systemd/knxd.service ${D}/${systemd_unitdir}/system
        install -m 644 ${S}/systemd/knxd.socket ${D}/${systemd_unitdir}/system
    else
        install -d ${D}/${sysconfdir}/init.d
        install -m 0655 ${S}/debian/knxd.init ${D}${sysconfdir}/init.d/knxd.init
    fi
}

PACKAGES =+ " ${PN}-exemples-dbg  ${PN}-exemples "

FILES_${PN} += " ${@bb.utils.contains('DISTRO_FEATURES', 'systemd','${systemd_unitdir} /usr/lib/sysusers.d', '', d)} "
FILES_${PN}-exemples += "/usr/share/knxd/examples"
FILES_${PN}-exemples-dbg += "/usr/share/knxd/examples/bin/.debug"
