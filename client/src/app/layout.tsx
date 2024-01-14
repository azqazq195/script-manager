import type {Metadata, Viewport} from 'next'
import {Inter} from 'next/font/google'
import './globals.css'
import {cn} from "@/lib/utils";
import {ThemeProvider} from "@/components/providers";
import {fontSans} from "@/lib/fonts";
import {Toaster} from "@/components/ui/toaster";
import {SiteHeader} from "@/components/site-header";
import * as React from "react";

const inter = Inter({subsets: ['latin']})

export const metadata: Metadata = {
  title: 'Create Next App',
  description: 'Generated by create next app',
}

export const viewport: Viewport = {
  themeColor: [
    {media: "(prefers-color-scheme: light)", color: "white"},
    {media: "(prefers-color-scheme: dark)", color: "black"},
  ],
}

interface RootLayoutProps {
  children: React.ReactNode
}

export default function RootLayout({children}: RootLayoutProps) {
  return (
    <>
      <html lang="en" suppressHydrationWarning>
      <body
        className={cn(
          "min-h-screen bg-background font-sans antialiased",
          fontSans.className
        )}
      >
      <ThemeProvider
        attribute="class"
        defaultTheme="dark"
        enableSystem
        disableTransitionOnChange
      >
        <div vaul-drawer-wrapper="">
          <div className={"md:hidden min-h-screen flex flex-col justify-center items-center"}>
            <h1>창민이가 만들어 주겠지.</h1>
          </div>
          <div className="hidden md:flex relative min-h-screen flex-col bg-background">
            <SiteHeader/>
            <main className="flex-1">
              {children}
            </main>
          </div>
        </div>
        <Toaster/>
      </ThemeProvider>
      </body>
      </html>
    </>
  )
}
