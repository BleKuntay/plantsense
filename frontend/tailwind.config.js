// tailwind.config.js
module.exports = {

  purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      colors: {
        "sidebar": "#525252",
        "primary": "#313131",
        "secondary": "#414141",
        "additional": "#CA3E47"
      }
    },
  },
  variants: {
    extend: {},
  },
  plugins: [
    // eslint-disable-next-line @typescript-eslint/no-require-imports
    require('daisyui'),
  ],
}
