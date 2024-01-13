"use client"

import Link from "next/link";
import {siteConfig} from "@/config/site";
import {cn} from "@/lib/utils";
import * as React from "react";
import {usePathname} from "next/navigation";
import {Icons} from "@/components/icons";

const menuItems = [
  {
    href: "/script",
    startWith: "/script",
    label: "Script"
  },
  {
    href: "/docker",
    startWith: "/docker",
    label: "Docker"
  },
  {
    href: "/settings/profile",
    startWith: "/settings",
    label: "Settings"
  },
];

export function SiteHeader() {
  const pathname = usePathname()

  return (
    pathname?.startsWith("/auth")
      ?
      <></>
      :
      <header className="sticky top-0 z-50 w-full border-b border-border/40 bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60">
        <div className="container flex h-14 max-w-screen-2xl items-center">
          <div className="mr-4 hidden md:flex">
            <Link href="/script" className="mr-6 flex items-center space-x-2">
              <Icons.logo className="h-6 w-6"/>
              <span className="hidden font-bold sm:inline-block">
                {siteConfig.name}
              </span>
            </Link>
            <nav className="flex items-center gap-6 text-sm">
              {menuItems.map(item => (
                <Link
                  key={item.href}
                  href={item.href}
                  className={cn(
                    "transition-colors hover:text-foreground/80",
                    pathname?.startsWith(item.startWith) ? "text-foreground" : "text-foreground/60"
                  )}
                >
                  {item.label}
                </Link>
              ))}
            </nav>
          </div>
          <div className="flex flex-1 items-center justify-between space-x-2 md:justify-end">
            <Link
              href={siteConfig.links.github}
              className={cn(
                "hidden text-foreground/60 transition-colors hover:text-foreground/80 lg:block"
              )}
            >
              GitHub
            </Link>
          </div>
        </div>
      </header>
  )
}
