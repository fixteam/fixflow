<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:import href="profile-docbook.xsl"/>
  
    <!-- Apply XHLTHL extension. -->
    <xsl:import href="highlight.xsl"/>
    <xsl:import href="../oxygen_custom_html.xsl"/>
    
    <xsl:param name="html.stylesheet">../../style.css</xsl:param>
    <xsl:param name="html.script.type">text/javascript</xsl:param>
    <xsl:param name="html.script">../../prettify.js</xsl:param>
    <xsl:template name="body.attributes">
        <xsl:attribute name="onLoad">prettyPrint();</xsl:attribute>
    </xsl:template>
 
    <xsl:template match="*" mode="class.value">
        <xsl:param name="class" select="local-name(.)"/>
        <xsl:if test="$class='programlisting'">
            <xsl:value-of select="'prettyprint'"/>
        </xsl:if>
    </xsl:template>
    

</xsl:stylesheet>