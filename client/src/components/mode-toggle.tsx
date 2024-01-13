"use client"


import {useTheme} from "next-themes";
import {Button} from "@/components/ui/button";
import {MoonIcon, SunIcon} from "@radix-ui/react-icons";

export function ModeToggle() {
  const {theme, setTheme} = useTheme()

  return (
    theme === "light"
      ?
      <Button variant="ghost" className="w-9 px-0" onClick={() => setTheme("dark")}>
        <MoonIcon className="h-[1.2rem] w-[1.2rem] rotate-0 scale-100 transition-all dark:-rotate-90 dark:scale-0"/>
      </Button>
      :
      <Button variant="ghost" className="w-9 px-0" onClick={() => setTheme("light")}>
        <SunIcon className="absolute h-[1.2rem] w-[1.2rem] rotate-90 scale-0 transition-all dark:rotae-0 dark:scale-100"/>
      </Button>

  )
}
