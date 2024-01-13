"use client"

import Link from "next/link";
import {siteConfig} from "@/config/site";
import {cn} from "@/lib/utils";
import * as React from "react";
import {useEffect} from "react";
import {usePathname} from "next/navigation";
import {Icons} from "@/components/icons";
import {buttonVariants} from "@/components/ui/button";
import {ModeToggle} from "@/components/mode-toggle";
import {NavigationMenu, NavigationMenuContent, NavigationMenuItem, NavigationMenuLink, NavigationMenuList, NavigationMenuTrigger, navigationMenuTriggerStyle} from "@/components/ui/navigation-menu";
import {projectSample} from "@/data/project";

const menuItems = [
  {
    href: "/docker",
    startWith: "/docker",
    label: "Docker"
  },
  {
    href: "/script",
    startWith: "/script",
    label: "Script"
  },
  {
    href: "/settings/account",
    startWith: "/settings",
    label: "Settings"
  },
];

type ProjectProps = {
  id: number,
  href: string,
  name: string,
  description: string
}

async function getProjectData(): Promise<ProjectProps[]> {
  return projectSample.map(project => ({
    ...project,
    href: `/project/${project.id}`
  }));
}

export function SiteHeader() {
  const pathname = usePathname()
  const [projects, setProject] = React.useState<ProjectProps[]>([])

  useEffect(() => {
    getProjectData().then(setProject)
    console.log(projects)
  }, [])

  return (
    pathname?.startsWith("/auth")
      ?
      <></>
      :
      <header className="sticky top-0 z-50 w-full border-b border-border/40 bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60">
        <div className="container flex h-14 max-w-screen-2xl items-center">
          {/* 왼쪽 블럭 */}
          <div className="mr-4 hidden md:flex">
            <div className="mr-6 flex items-center space-x-2">
              <Icons.logo className="h-6 w-6"/>
              <span className="hidden font-bold sm:inline-block" style={{userSelect: 'none'}}>
                {siteConfig.name}
              </span>
            </div>
            <NavigationMenu>
              <NavigationMenuList>
                <NavigationMenuItem className={cn(
                  "transition-colors hover:text-foreground/80",
                  pathname?.startsWith("/project") ? "text-foreground" : "text-foreground/60"
                )}>
                  <NavigationMenuTrigger>Projects</NavigationMenuTrigger>
                  <NavigationMenuContent>
                    <ul className="grid gap-3 p-4 md:w-[400px] lg:w-[500px] lg:grid-cols-[.75fr_1fr]">
                      <li className="row-span-12">
                        <NavigationMenuLink asChild>
                          <div
                            className="flex h-full w-full select-none flex-col justify-end rounded-md bg-gradient-to-b from-muted/50 to-muted p-6 no-underline outline-none focus:shadow-md"
                          >
                            <Icons.logo className="h-6 w-6"/>
                            <div className="mb-2 mt-4 text-lg font-medium">
                              shadcn/ui
                            </div>
                            <p className="text-sm leading-tight text-muted-foreground">
                              Beautifully designed components built with Radix UI and
                              Tailwind CSS.
                            </p>
                          </div>
                        </NavigationMenuLink>
                      </li>
                      {projects.map((component) => (
                        <ListItem
                          key={component.id}
                          title={component.name}
                          href={component.href}
                        >
                          {component.description}
                        </ListItem>
                      ))}
                      {/* TODO IF ADMIN */}
                      <ListItem
                        title={"프로젝트 추가하기"}
                        href={"/project/create"}
                      >
                        새로운 프로젝트를 생성합니다.
                      </ListItem>
                    </ul>
                  </NavigationMenuContent>
                </NavigationMenuItem>
                {menuItems.map(item => (
                  <NavigationMenuItem key={item.label} className={cn(
                    "transition-colors hover:text-foreground/80",
                    pathname?.startsWith(item.startWith) ? "text-foreground" : "text-foreground/60"
                  )}>
                    <Link href={item.href} legacyBehavior passHref>
                      <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                        {item.label}
                      </NavigationMenuLink>
                    </Link>
                  </NavigationMenuItem>
                ))}
              </NavigationMenuList>
            </NavigationMenu>
          </div>
          {/* 오른쪽 블럭 */}
          <div className="flex flex-1 items-center justify-between space-x-2 md:justify-end">
            <Link
              href={"/admin/users"}
              className={cn(
                "transition-colors hover:text-foreground/80",
                pathname?.startsWith("/admin") ? "text-foreground" : "text-foreground/60"
              )}
            >
              관리자
            </Link>
            <Link
              href={siteConfig.links.github}
              target="_blank"
              rel="noreferrer"
            >
              <div
                className={cn(
                  buttonVariants({
                    variant: "ghost",
                  }),
                  "w-9 px-0"
                )}
              >
                <Icons.gitHub className="h-4 w-4"/>
                <span className="sr-only">GitHub</span>
              </div>
            </Link>
            <ModeToggle/>
          </div>
        </div>
      </header>
  )
}

const ListItem = React.forwardRef<
  React.ElementRef<"a">,
  React.ComponentPropsWithoutRef<"a">
>(({className, title, children, ...props}, ref) => {
  return (
    <li>
      <NavigationMenuLink asChild>
        <a
          ref={ref}
          className={cn(
            "block select-none space-y-1 rounded-md p-3 leading-none no-underline outline-none transition-colors hover:bg-accent hover:text-accent-foreground focus:bg-accent focus:text-accent-foreground",
            className
          )}
          {...props}
        >
          <div className="text-sm font-medium leading-none">{title}</div>
          <p className="line-clamp-2 text-sm leading-snug text-muted-foreground">
            {children}
          </p>
        </a>
      </NavigationMenuLink>
    </li>
  )
})
ListItem.displayName = "ListItem"
