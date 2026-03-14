import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { Card, CardBody, CardHeader, Input, Button, Divider, useDisclosure } from '@heroui/react'
import { Eye, EyeOff, Mail, Lock, ArrowLeft } from 'lucide-react'
import { useAuth } from '../../context/AuthContext'
import ForgotPasswordModal from '../../components/auth/ForgotPasswordModal'

export default function Login() {
  const navigate = useNavigate()
  const { login, authenticating } = useAuth()
  const { isOpen: isForgotPasswordOpen, onOpen: onForgotPasswordOpen, onOpenChange: onForgotPasswordOpenChange } = useDisclosure()

  const [formData, setFormData] = useState({
    userName: '',
    password: ''
  })
  const [showPassword, setShowPassword] = useState(false)
  const [error, setError] = useState('')

  const handleChange = (field) => (value) => {
    setFormData(prev => ({
      ...prev,
      [field]: value
    }))
    // Effacer l'erreur quand l'utilisateur tape
    if (error) setError('')
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError('')

    // Validation basique
    if (!formData.userName.trim() || !formData.password) {
      setError('Veuillez remplir tous les champs')
      return
    }

    const result = await login(formData)

    if (result.success) {
      navigate('/', { replace: true })
    } else {
      setError(result.error || 'Erreur de connexion')
    }
  }

  const handleBack = () => {
    navigate('/')
  }

  return (
    <div className="min-h-screen bg-gray-900 flex items-center justify-center p-6">
      <div className="w-full max-w-md">
        {/* Bouton retour */}
        <Button
          variant="flat"
          startContent={<ArrowLeft size={16} />}
          onPress={handleBack}
          className="mb-6 bg-gray-800 hover:bg-gray-700 text-gray-200 border border-gray-700"
        >
          Retour à l'accueil
        </Button>

        <Card className="bg-gray-800 border border-gray-700 shadow-2xl">
          <CardHeader className="flex flex-col gap-3 pb-6 bg-gray-800">
            <div className="flex flex-col items-center">
              <div className="w-16 h-16 mb-4 bg-indigo-600 rounded-full flex items-center justify-center">
                <Lock className="text-white" size={24} />
              </div>
              <h1 className="text-2xl font-bold text-white">Connexion</h1>
              <p className="text-gray-400 text-center">
                Connectez-vous à votre compte LearnDev
              </p>
            </div>
          </CardHeader>

          <CardBody className="pt-0 bg-gray-800">
            <form onSubmit={handleSubmit} className="space-y-6">
              {/* Nom d'utilisateur */}
              <Input
                type="text"
                label="Nom d'utilisateur"
                placeholder="Entrez votre nom d'utilisateur"
                value={formData.userName}
                onValueChange={handleChange('userName')}
                startContent={<Mail className="text-gray-400" size={18} />}
                variant="bordered"
                classNames={{
                  input: "bg-transparent text-white",
                  inputWrapper: "bg-gray-700/50 border-gray-600 hover:border-gray-500 group-data-[focus=true]:border-indigo-500",
                  label: "text-gray-300"
                }}
                isRequired
              />

              {/* Mot de passe */}
              <Input
                type={showPassword ? 'text' : 'password'}
                label="Mot de passe"
                placeholder="Entrez votre mot de passe"
                value={formData.password}
                onValueChange={handleChange('password')}
                startContent={<Lock className="text-gray-400" size={18} />}
                endContent={
                  <Button
                    isIconOnly
                    variant="light"
                    onPress={() => setShowPassword(!showPassword)}
                    className="min-w-unit-8 w-unit-8 h-unit-8 text-gray-400 hover:text-gray-300"
                  >
                    {showPassword ?
                      <EyeOff className="text-gray-400" size={18} /> :
                      <Eye className="text-gray-400" size={18} />
                    }
                  </Button>
                }
                variant="bordered"
                classNames={{
                  input: "bg-transparent text-white",
                  inputWrapper: "bg-gray-700/50 border-gray-600 hover:border-gray-500 group-data-[focus=true]:border-indigo-500",
                  label: "text-gray-300"
                }}
                isRequired
              />

              {/* Message d'erreur */}
              {error && (
                <div className="text-red-400 text-sm text-center bg-red-500/10 border border-red-500/30 rounded-lg p-3">
                  {error}
                </div>
              )}

              {/* Bouton de connexion */}
              <Button
                type="submit"
                size="lg"
                className="w-full font-semibold bg-indigo-600 hover:bg-indigo-700 text-white"
                isLoading={authenticating}
                isDisabled={!formData.userName.trim() || !formData.password}
              >
                {authenticating  ? 'Connexion...' : 'Se connecter'}
              </Button>
            </form>

            <Divider className="my-6 bg-gray-700" />

            {/* Liens */}
            <div className="space-y-4 text-center">
              <Button
                variant="light"
                size="sm"
                onPress={onForgotPasswordOpen}
                className="text-indigo-400 hover:text-indigo-300 text-sm transition-colors"
              >
                Mot de passe oublié ?
              </Button>

              <div className="text-gray-400 text-sm">
                Pas encore de compte ?{' '}
                <Link
                  to="/register"
                  className="text-indigo-400 hover:text-indigo-300 font-medium transition-colors"
                >
                  S'inscrire
                </Link>
              </div>
            </div>
          </CardBody>
        </Card>

        {/* Modal mot de passe oublié */}
        <ForgotPasswordModal
          isOpen={isForgotPasswordOpen}
          onOpenChange={onForgotPasswordOpenChange}
        />
      </div>
    </div>
  )
}