package net.pfiers.osmfocus.osmapi

import org.locationtech.jts.geom.Envelope


fun Envelope.toApiBboxStr() =
    listOf(minX, minY, maxX, maxY).joinToString(",")
